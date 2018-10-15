Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。
Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。

简而言之：

Feign 采用的是基于接口的注解
Feign 整合了ribbon，具有负载均衡的能力
整合了Hystrix，具有熔断的能力

Feign中Hystrix的配置和Ribbon有点像，基础配置如下：

# 设置熔断超时时间
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds=10000
# 关闭Hystrix功能（不要和上面的配置一起使用）
feign.hystrix.enabled=false
# 关闭熔断功能
hystrix.command.default.execution.timeout.enabled=false
这种配置也是全局配置，如果我们想针对某一个接口配置，比如/hello接口，那么可以按照下面这种写法，如下：

# 设置熔断超时时间
hystrix.command.hello.execution.isolation.thread.timeoutInMilliseconds=10000
# 关闭熔断功能
hystrix.command.hello.execution.timeout.enabled=false


eign为每一个FeignClient都提供了一个feign.Logger实例，我们可以在配置中开启日志，开启方式很简单，分两步：

第一步：application.properties中配置日志输出 
application.properties中配置如下内容，表示设置日志输出级别：

# 开启日志 格式为logging.level.+Feign客户端路径
logging.level.org.sang.HelloService=debug

第二步：入口类中配置日志Bean

入口类中配置日志Bean，如下：

@Bean
Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
}














从代码@ConditionalOnClass(ApacheHttpClient.class)注解可知道，
只需要在pom文件加上HttpClient的classpath就行了，
另外需要在配置文件上加上feign.httpclient.enabled为true，
从 @ConditionalOnProperty注解可知，这个可以不写，在默认的情况下就为true.

在pom文件加上：


<dependency>
    <groupId>com.netflix.feign</groupId>
    <artifactId>feign-httpclient</artifactId>
    <version>RELEASE</version>
</dependency>




@ConditionalOnClass({ ILoadBalancer.class, Feign.class })
@Configuration
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignRibbonClientAutoConfiguration {

    @Configuration
	@ConditionalOnClass(ApacheHttpClient.class)
	@ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
	protected static class HttpClientFeignLoadBalancedConfiguration {

		@Autowired(required = false)
		private HttpClient httpClient;

		@Bean
		@ConditionalOnMissingBean(Client.class)
		public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
				SpringClientFactory clientFactory) {
			ApacheHttpClient delegate;
			if (this.httpClient != null) {
				delegate = new ApacheHttpClient(this.httpClient);
			}
			else {
				delegate = new ApacheHttpClient();
			}
			return new LoadBalancerFeignClient(delegate, cachingFactory, clientFactory);
		}
	}
}



Spring Cloud Feign支持对请求和响应进行GZIP压缩，以提高通信效率，配置方式如下：

# 配置请求GZIP压缩
feign.compression.request.enabled=true
# 配置响应GZIP压缩
feign.compression.response.enabled=true
# 配置压缩支持的MIME TYPE
feign.compression.request.mime-types=text/xml,application/xml,application/json
# 配置压缩数据大小的下限
feign.compression.request.min-request-size=2048