https://blog.csdn.net/qq_38439885/article/details/81174635

@Retryable注解 
被注解的方法发生异常时会重试 
value:指定发生的异常进行重试 
include:和value一样，默认空，当exclude也为空时，所有异常都重试 
exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试 
maxAttemps:重试次数，默认3 
backoff:重试补偿机制，默认没有 delay:指定延迟后重试 
                    multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，
                    第一次重试为5秒后，第二次为10秒，第三次为20秒
                    @Backoff：重试回退策略（立即重试还是等待一会再重试） 
                    不设置参数时，默认使用FixedBackOffPolicy，重试等待1000ms 
                    只设置delay()属性时，使用FixedBackOffPolicy，重试等待指定的毫秒数 
                    当设置delay()和maxDealy()属性时，重试等待在这两个值之间均态分布 
                    使用delay()，maxDealy()和multiplier()属性时，使用ExponentialBackOffPolicy 
                    当设置multiplier()属性不等于0时，同时也设置了random()属性时，使用ExponentialRandomBackOffPolicy
label: 重试的名字，系统唯一 


@CircuitBreaker：用于方法，实现熔断模式。 
include 指定处理的异常类。默认为空 
exclude指定不需要处理的异常。默认为空 
vaue指定要重试的异常。默认为空 
maxAttempts 最大重试次数。默认3次 
openTimeout 配置熔断器打开的超时时间，默认5s，当超过openTimeout之后熔断器电路变成半打开状态（只要有一次重试成功，则闭合电路） 
resetTimeout 配置熔断器重新闭合的超时时间，默认20s，超过这个时间断路器关闭


@Recover 
当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。
需要注意的是发生的异常和入参类型一致时才会回调
用于@Retryable失败时的“兜底”处理方法。 
@Recover注释的方法必须要与@Retryable注解的方法“签名”保持一致，第
一入参为要重试的异常，其他参数与@Retryable保持一致，返回值也要一样，否则无法执行！


只读操作可以重试，幂等写操作可以重试，
但是非幂等写操作不能重试，重试可能导致脏写，或产生重复数据。

无状态重试，是在一个循环中执行完重试策略，
即重试上下文保持在一个线程上下文中，在一次调用中进行完整的重试策略判断。
非常简单的情况，如远程调用某个查询方法时是最常见的无状态重试。

有状态重试，有两种情况需要使用有状态重试，事务操作需要回滚或者熔断器模式。 
事务操作需要回滚场景时，当整个操作中抛出的是数据库异常DataAccessException，则不能进行重试需要回滚，而抛出其他异常则可以进行重试，可以通过RetryState实现：









