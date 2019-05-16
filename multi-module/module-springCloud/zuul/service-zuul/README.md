 **[zuul博客](http://www.ymq.io/2017/12/11/spring-cloud-zuul-filter/)**

##Zuul 管理端点  
    http://localhost:8769/actuator/routes

注意在使用Http访问端点时，需要加上默认/actuator 前缀
management:
  endpoints:
    web:
      exposure:
        include: "*"


filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，
具体如下：
pre：路由之前
routing：路由之时
post： 路由之后
error：发送错误调用
filterOrder：过滤的顺序
shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问


实现自定义Filter，需要继承ZuulFilter的类，并覆盖其中的4个方法。

    public class MyFilter extends ZuulFilter {
        @Override
        String filterType() {
            return "pre"; //定义filter的类型，有pre、route、post、error四种
        }
    
        @Override
        int filterOrder() {
            return 10; //定义filter的顺序，数字越小表示顺序越高，越先执行
        }
    
        @Override
        boolean shouldFilter() {
            return true; //表示是否需要执行该filter，true表示执行，false表示不执行
        }
    
        @Override
        Object run() {
            return null; //filter需要执行的具体操作
        }
    }
    
我们可以使用“PRE"类型的Filter做很多的验证工作，在实际使用中我们可以结合shiro、oauth2.0等技术去做鉴权、验证。


##重试
有时候我们不希望所有路由都有重试机制，我们可以配置指定路由进行重试:

zuul.routes.<routename>.retryable=true
这里的routename默认情况下就是你的服务名(我们可以通过管理端点/routes看到都有哪些路由,
也可以查看更详细的路由信息：/routes?format=details,端点实现类:org.springframework.cloud.netflix.zuul.RoutesMvcEndpoint)。
例如我有一个rcmd-service-data的服务，我可以这样配置:

zuul.retryable=false
zuul.routes.rcmd-service-data.retryable=true

我们知道zuul请求也是通过Ribbon负载均衡客户端去调用其他服务的，
所以我们的重试策略也是在具体的ribbon配置中指定:

rcmd-service-data:
  ribbon:
    # Max number of retries on the same server (excluding the first try)
    MaxAutoRetries: 1 
    # Max number of next servers to retry (excluding the first server)
    MaxAutoRetriesNextServer: 2 #当允许在其他服务器上重试的时候，会调用IRule.choose选择可用服务实例中的其他一台服务实例进行调用
    # Whether all operations can be retried for this client
    OkToRetryOnAllOperations: true  #默认为false,则只允许GET请求被重试
    ReadTimeout: 5000
    ConnectTimeout: 2000


重试的时候还有补偿策略，例如重试时间间隔（默认是没有间隔：org.springframework.retry.backoff.NoBackOffPolicy），我们可以实现自己的补偿策略，
也可以用内部实现的一些补偿策略(需要定义一个bean)，如指数级的补偿策略(1秒，2秒，4秒类似这种指数级睡眠间隔增长，不超过10秒):
@Configuration
public class MyConfiguration {
    @Bean
    LoadBalancedBackOffPolicyFactory backOffPolciyFactory() {
        return new LoadBalancedBackOffPolicyFactory() {
            @Override
            public BackOffPolicy createBackOffPolicy(String service) {
                return new ExponentialBackOffPolicy();
            }
        };
    }
}

也可以正对某些响应状态码进行重试(当调用rcmd-service-data返回404,502的时候，进行重试，其他状态码不重试):

rcmd-service-data:
  ribbon:
    retryableStatusCodes: 404,502
    
https://blog.csdn.net/xiao_jun_0820/article/details/79320352

