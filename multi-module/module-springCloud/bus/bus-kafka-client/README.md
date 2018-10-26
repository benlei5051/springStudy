curl -X POST http://localhost:8889/actuator/bus-refresh

注意，因为我们使用的 spring cloud 的是 F 版本，与低版本有所不同，这里暴露的端点是  /actuator/bus-refresh，这个在之前的文章中有讲解过。
新版本有两个坑，就是上面所说的一个发送的 post 请求的 URL 有变化，暴露的端口为 /actuator/bus-refresh；
还有一个就是要加一个 @RefreshScope 注解

先后启动eureka-server、config-server、bus-kafka-client
