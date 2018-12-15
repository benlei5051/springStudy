
为什么要用分布式锁： 原理漫画介绍   http://www.360doc.com/content/18/0528/08/36490684_757590223.shtml

## 1.实现分布式锁的几种方案
    1.Redis实现   (推荐)
    2.Zookeeper实现
    3.数据库实现
Redis实现分布式锁
*
* 在集群等多服务器中经常使用到同步处理一下业务，这是普通的事务是满足不了业务需求，需要分布式锁
*
* 分布式锁的常用3种实现：
*        0.数据库乐观锁实现
*        1.Redis实现  --- 使用redis的setnx()、get()、getset()方法，用于分布式锁，解决死锁问题
*        2.Zookeeper实现
*           参考：http://surlymo.iteye.com/blog/2082684
*              //www.jb51.net/article/103617.htm
*              http://www.hollischuang.com/archives/1716?utm_source=tuicool&utm_medium=referral
1、实现原理：
基于zookeeper瞬时有序节点实现的分布式锁，其主要逻辑如下（该图来自于IBM网站）。大致思想即为：每个客户端对某个功能加锁时，在zookeeper上的与该功能对应的指定节点的目录下，生成一个唯一的瞬时有序节点。判断是否获取锁的方式很简单，只需要判断有序节点中序号最小的一个。当释放锁的时候，只需将这个瞬时节点删除即可。同时，其可以避免服务宕机导致的锁无法释放，而产生的死锁问题。
2、优点
锁安全性高，zk可持久化
3、缺点
性能开销比较高。因为其需要动态产生、销毁瞬时节点来实现锁功能。
4、实现
可以直接采用zookeeper第三方库curator即可方便地实现分布式锁
*
* Redis实现分布式锁的原理：
*  1.通过setnx(lock_timeout)实现，如果设置了锁返回1， 已经有值没有设置成功返回0
*  2.死锁问题：通过实践来判断是否过期，如果已经过期，获取到过期时间get(lockKey)，然后getset(lock_timeout)判断是否和get相同，
*   相同则证明已经加锁成功，因为可能导致多线程同时执行getset(lock_timeout)方法，这可能导致多线程都只需getset后，对于判断加锁成功的线程，
*   再加expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS)过期时间，防止多个线程同时叠加时间，导致锁时效时间翻倍
*  3.针对集群服务器时间不一致问题，可以调用redis的time()获取当前时间