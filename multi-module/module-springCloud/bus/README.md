https://www.cnblogs.com/cralor/p/9239976.html



1，启动kafka客户端
2、分别启动项目 bus-eureka-server, bus-kafka-server,bus-kafka-client
3，启动bus-kafak-client工程时注意：
   运行ClientApplication之前，将MyController中方法名publish中的变量修改为final String myUniqueId = "config-client1:7001";
   运行Client2Application之前，将MyController中方法名publish中的变量修改为final String myUniqueId = "config-client1:7002";
  
4、测试bus-kafka-server是否可以从git中读取数据，请求以下路径
      http://localhost:8081/test/dev
      
      http请求地址和资源文件映射如下:
      /{application}/{profile}[/{label}]
      /{application}-{profile}.yml
      /{label}/{application}-{profile}.yml
      /{application}-{profile}.properties
      /{label}/{application}-{profile}.properties
      其中{label}是指分支，默认是master。
      
5、测试 bus-kafka-client 能否读取到配置属性
    
    GET http://localhost:7001/from
    GET http://localhost:7002/from

6、更改git仓库中的数据后，调用下面的接口刷新每个微服务的配置信息
    
    curl -X POST http://localhost:8081/actuator/bus-refresh

7、刷新某一个服务的配置

    POST请求
    
    错误的url  http://localhost:8081/actuator/bus-refresh/{destination}=config-client1
    
    正确的url  http://localhost:8081/actuator/bus-refresh/config-client1




注意事项:
    
  properties文件配置

    management.endpoints.web.exposure.include=bus-refresh
    management.endpoints.web.exposure.include=bus-env
   
    测试用以下配置：
    application.properties
    
         management.endpoints.web.exposure.include=*
    
    或者yml文件配置
        management:
          endpoints:
            web:
              exposure:
                include: '*'
        




bus-kafka-client配置中心的地址一定要放在bootstrap.properties（yml）中

spring cloud 的是 F 版本，与低版本有所不同，这里暴露的端点是  /actuator/bus-refresh，
新版本有两个坑，就是上面所说的一个发送的 post 请求的 URL 有变化，暴露的端口为 /actuator/bus-refresh；
还有一个就是要加一个 @RefreshScope 注解





