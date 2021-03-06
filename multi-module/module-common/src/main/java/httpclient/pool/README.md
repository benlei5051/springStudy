Timer类负责管理延迟任务（“在10ms后执行该任务”）以及周期任务（“每10ms执行一次该任务”）。然而，Timer存在一些缺陷，因此应该考虑使用ScheduledThreadPoolExecutor来代替它（Timer支持基于绝对时间而不是相对时间的调度机制，因此任务的执行对系统时钟变化很敏感，而ScheduledThreadPoolExecutor只支持基于相对时间的调度）。可以通过ScheduledThreadPoolExecutor的构造函数或newScheduledThreadPool工厂方法来创建该类的对象。

Timer在执行所有定时任务时只会创建一个线程。如果某个任务的执行时间过长，那么将破坏其他TimerTask的精确性。例如某个周期TimerTask需要每10ms执行一次，而另一个TimerTask需要执行40ms，那么这个周期任务要么在40ms任务执行后快速连续地调用4次，要么将彻底“丢失”4次调用（取决于它是基于固定速率来调度还是基于固定延迟来调度）。线程池能弥补这个缺陷，它可以提供多个线程来执行延迟任务和周期任务。

Timer的另一个问题是，如果TimerTask抛出了一个未检查的异常，那么Timer将表现出糟糕的行为。Timer线程并不捕捉异常，因此当TimerTask抛出异常时，将终止定时线程。这种情况下，Timer也不会恢复线程的执行，而是会错误的认为整个Timer都被取消了。因此已经被调度单稍微执行的TimerTask将不会再执行，新的任务也不能被调度（这个问题称为“线程泄露[Thread Leake]”）。

程序OutOfTime中给出了Timer中为什么会出现这种问题，以及如何使得试图提交TimerTask的调用者也出现问题。你可能认为程序会运行6秒后退出，但实际情况是运行1秒就结束了，并抛出一个异常信息“Timer already cancelled”。 ScheduledThreadPoolExecutor能正确处理这些表现出错误行为的任务。在Java 5.0或更高版本的JDK中，将很少使用Timer。