https://blog.csdn.net/u010723709/article/details/50377543

https://www.cnblogs.com/wihainan/p/4765862.html

ThreadPoolExecutor
先看看如何使用ThreadPoolExecutor创建线程池：

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) 
corePoolSize - 线程池核心池的大小。
maximumPoolSize - 线程池的最大线程数。
keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
unit - keepAliveTime 的时间单位。
workQueue - 用来储存等待执行任务的队列。
threadFactory - 线程工厂。
handler - 拒绝策略。

关注点1 线程池大小
线程池有两个线程数的设置，一个为核心池线程数，一个为最大线程数。
在创建了线程池后，默认情况下，线程池中并没有任何线程，等到有任务来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法
当创建的线程数等于 corePoolSize 时，会加入设置的阻塞队列。当队列满时，会创建线程执行任务直到线程池中的数量等于maximumPoolSize。

关注点2 适当的阻塞队列
java.lang.IllegalStateException: Queue full
方法 抛出异常 返回特殊值 一直阻塞 超时退出
插入方法 add(e) offer(e) put(e) offer(e,time,unit)
移除方法 remove() poll() take() poll(time,unit)
检查方法 element() peek() 不可用 不可用

ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
LinkedBlockingQueue ：一个由链表结构组成的有界阻塞队列。
PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
DelayQueue： 一个使用优先级队列实现的无界阻塞队列。
SynchronousQueue： 一个不存储元素的阻塞队列。
LinkedTransferQueue： 一个由链表结构组成的无界阻塞队列。
LinkedBlockingDeque： 一个由链表结构组成的双向阻塞队列。

关注点3 明确拒绝策略
ThreadPoolExecutor.AbortPolicy: 丢弃任务并抛出RejectedExecutionException异常。 (默认)
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务

说明：Executors 各个方法的弊端：
1）newFixedThreadPool 和 newSingleThreadExecutor:
主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至 OOM。
2）newCachedThreadPool 和 newScheduledThreadPool:
主要问题是线程数最大数是 Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至 OOM。

Executors
让我们再看看Executors提供的那几个工厂方法。

newSingleThreadExecutor
创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
此线程池保证所有任务的执行顺序按照任务的提交顺序执行。

new ThreadPoolExecutor(1, 1,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
newFixedThreadPool
创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。

new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
newCachedThreadPool
创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。

new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());



这里简单说明下，关于ThreadPoolTaskExecutor参数说明：

corePoolSize：线程池维护线程的最少数量
keepAliveSeconds：允许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
maxPoolSize：线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
queueCapacity：缓存队列
rejectedExecutionHandler：线程池对拒绝任务（无线程可用）的处理策略。这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
还有一个是AbortPolicy策略：处理程序遭到拒绝将抛出运行时RejectedExecutionException。
而在一些场景下，若需要在关闭线程池时等待当前调度任务完成后才开始关闭，
可以通过简单的配置，进行优雅的停机策略配置。
关键就是通过setWaitForTasksToCompleteOnShutdown(true)和setAwaitTerminationSeconds方法。

setWaitForTasksToCompleteOnShutdown:表明等待所有线程执行完，默认为false。
setAwaitTerminationSeconds:等待的时间，因为不能无限的等待下去。
所以，线程池完整配置为：
@Bean(name = "asyncPoolTaskExecutor")
public ThreadPoolTaskExecutor getAsyncThreadPoolTaskExecutor() {
ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
taskExecutor.setCorePoolSize(20);
taskExecutor.setMaxPoolSize(200);
taskExecutor.setQueueCapacity(25);
taskExecutor.setKeepAliveSeconds(200);
taskExecutor.setThreadNamePrefix("oKong-");
// 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
//AbortPolicy:直接抛出java.util.concurrent.RejectedExecutionException异常 -->
//CallerRunsPolicy:主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，可以有效降低向线程池内添加任务的速度 -->
//DiscardOldestPolicy:抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->
//DiscardPolicy:抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行 -->

建议大家用CallerRunsPolicy策略，因为当队列中的任务满了之后，如果直接抛异常，
那么这个任务就会被丢弃，如果是CallerRunsPolicy策略会用主线程去执行，就是同步执行，
最起码这样任务不会丢弃。


taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//调度器shutdown被调用时等待当前被调度的任务完成
taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
//等待时长
taskExecutor.setAwaitTerminationSeconds(60);
taskExecutor.initialize();
return taskExecutor;
}




















1  corePoolSize（线程池的基本大小）:当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。

2  workQueue(任务队列) : 用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列：

ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，按FIFO原则进行排序
LinkedBlockingQueue:一个基于链表结构的阻塞队列，吞吐量高于ArrayBlockingQueue。静态工厂方法Excutors.newFixedThreadPool()使用了这个队列
SynchronousQueue: 一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量高于LinkedBlockingQueue，静态工厂方法Excutors.newCachedThreadPool()使用了这个队列
PriorityBlockingQueue:一个具有优先级的无限阻塞队列。
3  maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界的任务队列这个参数就没用了。
4 threadFactory（线程工厂）：可以通过线程工厂为每个创建出来的线程设置更有意义的名字，如开源框架guava
5 RejectedExecutionHandler （饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略还处理新提交的任务。它可以有如下四个选项：
AbortPolicy:直接抛出异常，默认情况下采用这种策略
CallerRunsPolicy:只用调用者所在线程来运行任务
DiscardOldestPolicy:丢弃队列里最近的一个任务，并执行当前任务
DiscardPolicy:不处理，丢弃掉
    更多的时候，我们应该通过实现RejectedExecutionHandler 接口来自定义策略，比如记录日志或持久化存储等。
6 keepAliveTime(线程活动时间):线程池的工作线程空闲后，保持存活的时间。所以如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程利用率。

7 TimeUnit(线程活动时间的单位)：可选的单位有天（Days）、小时（HOURS）、分钟（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）。
2 提交任务

可以使用execute和submit两个方法向线程池提交任务

（1）execute方法用于提交不需要返回值的任务，利用这种方式提交的任务无法得知是否正常执行
threadPoolExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
（2） submit方法用于提交一个任务并带有返回值，这个方法将返回一个Future类型对象。可以通过这个返回对象判断任务是否执行成功，并且可以通过future.get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成。
Future<?> future=threadPoolExecutor.submit(futureTask);
		
		Object value=future.get();
		
		3 关闭线程池
        
        可以通过调用线程池的shutdown或shutdownNow方法来关闭线程池。他们的原理是遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程，所以无响应中断的任务可能永远无法停止。但是他们存在一定的区别，shutdownNow首先将线程池的状态设置为STOP，然后尝试停止所有正在执行或暂停任务的线程，并返回等待执行任务的列表，而shutdown只是将线程池的状态设置成SHUTDOWN状态，然后中断所有正在执行的任务。
        
        只要调用了这两个关闭方法的一个，isShutdown就会返回true。当所有的任务都关闭后，才表示线程池关闭成功，这是调用isTerminated方法会返回true。至于应该调用哪一种方法来关闭线程池，应该由提交到线程池的任务特性决定，通常调用shutdown方法来关闭线程池，如果任务不一定执行完，则可以调用shutdownNow方法。
        
        4 合理配置线程池
        要想合理地配置线程池，首先要分析任务特性
        任务的性质：CPU密集型任务、IO密集型任务和混合型任务。
        任务的优先级：高、中和低。
        任务的执行时间：长、中和短。
        任务的依赖性：是否依赖其他系统资源，如数据库连接。
        性质不同的任务可以用不同规模的线程池分开处理。CPU密集型任务应该配置尽可能少的线程，如配置N+1个线程，N位CPU的个数。而IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如2*N。混合型任务，如果可以拆分，将其拆分成一个CPU密集型任务和一个IO密集型任务，只要这两个任务执行的时间相差不是太大，那么分解后执行的吞吐量将高于串行执行的吞吐量
        优先级不同的任务可以交给优先级队列PriorityBlcokingQueue来处理。
        执行时间不同的任务可以交给不同规模的线程池来处理。
        依赖数据库的任务，因此线程提交SQL后需要等待数据库返回结果，等待的时间越长，则CPU空闲时间越长，那么线程数应该设置的越大，这样能更好滴利用CPU。
        
        5 线程池应用示例
        1 示例1 验证shutdown和shutdownNow的区别
        
        （1）首先构造一个线程池，用ArrayBlockingQueue作为其等待队列，队列初始化容量为10。该线程池核心容量为 10，最大容量为20，线程存活时间为1分钟。
        
        static BlockingQueue blockingQueue=new ArrayBlockingQueue<>(10);
        	
        	static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, blockingQueue);
        
        
        （2）另外构造了一个实现Runable接口的类TaskWithoutResult，其逻辑很简单，睡眠1秒
        /**
         * 无返回值的任务
         * @author songxu
         *
         */
        class TaskWithoutResult implements Runnable
        {
        	private int sleepTime=1000;//默认睡眠时间1s
        	public TaskWithoutResult(int sleepTime)
        	{
        		this.sleepTime=sleepTime;
        	}
        	@Override
        	public void run() 
        	{
        		System.out.println("线程"+Thread.currentThread()+"开始运行");
        		try {
        			Thread.sleep(sleepTime);
        		} catch (InterruptedException e) {//捕捉中断异常
        			
        			System.out.println("线程"+Thread.currentThread()+"被中断");
        		}
        		System.out.println("线程"+Thread.currentThread()+"结束运行");
        	}
        	
         
         
        }
        
        （3）验证
        /**
        	 * 中断测试
        	 */
        	public static void  test1()
        	{
        		for(int i=0;i<10;i++)
        		{
        			Runnable runnable=new TaskWithoutResult(1000);
        			threadPoolExecutor.submit(runnable);
        		}
        		//threadPoolExecutor.shutdown();//不会触发中断
        		threadPoolExecutor.shutdownNow();//会触发中断
        	}
        
        分别测试shutdown和shutdownNow()方法，结果shutdown()方法的调用并不会引发中断，而shutdownNow()方法则会引发中断。这也正验证前面所说的，shutdown方法只是发出了停止信号，等所有线程执行完毕会关闭线程池；而shutdownNow则是立即停止所有任务。
        
        
        2 示例2 验证线程池的扩容
        在本例中想要验证线程池扩容到核心数量，然后再扩容到最大数量，最后再缩小到核心数量的过程。
        
        （1）首先构造一个线程池，用ArrayBlockingQueue作为其等待队列，队列初始化容量为1。该线程池核心容量为 10，最大容量为20，线程存活时间为1分钟。
          
        static BlockingQueue blockingQueue=new ArrayBlockingQueue<>(1);
        	
        	static ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, blockingQueue);
        
         （2）另外构造了一个实现Runable接口的类TaskBusyWithoutResult类，其模拟一个繁忙的任务
         
        class TaskBusyWithoutResult implements Runnable
        {
        	public TaskBusyWithoutResult()
        	{
        	}
        	@Override
        	public void run() 
        	{
        		System.out.println("线程"+Thread.currentThread()+"开始运行");
        		int i=10000*10000;
        		while(i>0)
        		{
        			i--;
        		}
        		System.out.println("线程"+Thread.currentThread()+"运行结束");
        	}
        	
         
         
        }
         （3）测试验证，向线程池提交20个任务
        /**
        	 * 扩容测试
        	 */
        	public static void  test2()
        	{
        		for(int i=0;i<20;i++)
        		{
        			Runnable runnable=new TaskBusyWithoutResult();
        			threadPoolExecutor.submit(runnable);
        		}
        	}


