 分布式锁简单入门以及三种实现方式介绍
 https://blog.csdn.net/xlgen157387/article/details/79036337
 
 
 最近遇到了一个问题，有一个定时任务：例如定时从数据库中查询所有的待执行的任务，然后执行
 。为了提高整个程序的效率，我们使用了线程池，但是在实际的测试中，却发现了一个问题，
 如果线程池的线程数为5，那么这个定时任务就会被执行5次(当然，正常情况下，提交一个任务到线程池，
 是只有一个线程执行的，但是生产中我们用了一个第三方的框架，线程池定义多少个，
 就会有多少个线程执行这个定时任务，这点很奇葩啊)，也就是说，本来只希望只执行一次的，但是却重复执行了
 。那么怎么保证线程池中只有一个线程执行这个定时任务了，当然解决的方案有很多，
 例如使用Redis或zookeeper加全局锁等，今天，我们就通过使用数据库模拟状态机来实现乐观锁来解决这个问题。

实现机制： 使用数据版本（Version）记录机制实现，这是乐观锁最常用的一种实现方式。
何谓数据版本？即为数据增加一个版本标识，
一般是通过为数据库表增加一个数字类型的 “version” 字段来实现。
当读取数据时，将version字段的值一同读出，数据每更新一次，
对此version值加一。当我们提交更新的时候，
判断数据库表对应记录的当前版本信息与第一次取出来的version值进行比对
，如果数据库表当前版本号与第一次取出来的version值相等，则可以理解为加锁，
如果取出的当前version的值与第一次取出来的值不等，就会更新失败，
我们可以把这个过程理解为加锁失败，只有加锁成功的那个线程才能进行后面的业务逻辑操作，
这就解决了上面遇到的那个问题。

create table account_wallet
(
   id                   int not null comment '用户钱包主键',
   user_open_id         varchar(64) comment '用户中心的用户唯一编号',
   user_amount          decimal(10,5),
   create_time          datetime,
   update_time          datetime,
   pay_password         varchar(64),
   is_open              int comment '0:代表未开启支付密码，1:代表开发支付密码',
   check_key            varchar(64) comment '平台进行用户余额更改时，首先效验key值，否则无法进行用户余额更改操作',
   version              int comment '基于mysql乐观锁，解决并发访问'
   primary key (id)
);
    
    <!--通过用户唯一编号，查询用户钱包相关的信息  -->
      <select id="selectByOpenId" resultMap="BaseResultMap" parameterType="java.lang.String">
         select 
        <include refid="Base_Column_List" />
        from account_wallet
        where user_open_id = #{openId,jdbcType=VARCHAR}
      </select>
      
      <!--用户钱包数据更改 ，通过乐观锁(version机制)实现 -->
      <update id="updateAccountWallet" parameterType="com.settlement.model.AccountWallet">
              <![CDATA[
                update account_wallet set user_amount = #{userAmount,jdbcType=DECIMAL}, version = version + 1 where id =#{id,jdbcType=INTEGER} and version = #{version,jdbcType=INTEGER} 
               ]]> 
      </update>


