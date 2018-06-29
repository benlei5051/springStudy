# springboot-security-oauth2
### Spring集成Spring Security、OAuth2实现资源访问授权认证。
### 后端主要做的是认证服务器和资源服务。
### 用户登录使用自定义UserDetailService从MySQL中加载数据。
### 用户认证的token使用RedisTokenStore保存在redis中。


##获取token


http://localhost:1130/oauth/token?grant_type=password&username=bob&password=abc
Method:POST
需要在authorization中Basic Auth中的用户名输入my-trusted-client，密码是secret

        {
            "access_token": "f1e28ea7-8d4b-4a85-a2f0-70b0d383163b",
            "token_type": "bearer",
            "refresh_token": "19e03aae-0fab-4234-87ec-3b328f376be9",
            "expires_in": 599,
            "scope": "read write trust"
        }
##刷新token   
http://localhost:1130/oauth/token?grant_type=refresh_token&refresh_token=80dcc32d-e103-42a0-9ca4-993dfeea200b
需要在authorization中Basic Auth中的用户名输入my-trusted-client，密码是secret



###第三方应用：
    
>第三方应用程序俗称为客户端，将尝试获得访问用户的帐户。它需要从用户获得许可，才可以这样做。这可能是一个基于Web服务器的应用程序，基于浏览器的应用程序，桌面应用程序，手机/平板电脑应用程序或一些智能设备，如谷歌护目镜和智能电视。


### 授权服务配置

>     可以用 @EnableAuthorizationServer 注解来配置OAuth2.0 授权服务机制，通过使用@Bean注解的几个方法一起来配置这个授权服务。下面咱们介绍几个配置类，这几个配置是由Spring创建的独立的配置对象，它们会被Spring传入AuthorizationServerConfigurer中：
     
####  ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。

      配置客户端详情信息（Client Details)：
      ClientDetailsServiceConfigurer (AuthorizationServerConfigurer 的一个回调配置项，见上的概述) 能够使用内存或者JDBC来实现客户端详情服务（ClientDetailsService），有几个重要的属性如下列表：
      clientId：（必须的）用来标识客户的Id。
      secret：（需要值得信任的客户端）客户端安全码，如果有的话。
      scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
      authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
      authorities：此客户端可以使用的权限（基于Spring Security authorities）。
        


####  AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
      
     
        
>     AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。


      授权是使用 AuthorizationEndpoint 这个端点来进行控制的，你能够使用 AuthorizationServerEndpointsConfigurer 这个对象的实例来进行配置(AuthorizationServerConfigurer 的一个回调配置项，见上的概述) ，如果你不进行设置的话，默认是除了资源所有者密码（password）授权类型以外，支持其余所有标准授权类型的（RFC6749），我们来看一下这个配置对象有哪些属性可以设置吧，如下列表：
      authenticationManager：认证管理器，当你选择了资源所有者密码（password）授权类型的时候，请设置这个属性注入一个 AuthenticationManager 对象。
      userDetailsService：如果啊，你设置了这个属性的话，那说明你有一个自己的 UserDetailsService 接口的实现，或者你可以把这个东西设置到全局域上面去（例如 GlobalAuthenticationManagerConfigurer 这个配置对象），当你设置了这个之后，那么 "refresh_token" 即刷新令牌授权类型模式的流程中就会包含一个检查，用来确保这个账号是否仍然有效，假如说你禁用了这个账户的话。
      authorizationCodeServices：这个属性是用来设置授权码服务的（即 AuthorizationCodeServices 的实例对象），主要用于 "authorization_code" 授权码类型模式。
      implicitGrantService：这个属性用于设置隐式授权模式，用来管理隐式授权模式的状态。
      tokenGranter：这个属性就很牛B了，当你设置了这个东西（即 TokenGranter 接口实现），那么授权将会交由你来完全掌控，并且会忽略掉上面的这几个属性，这个属性一般是用作拓展用途的，即标准的四种授权模式已经满足不了你的需求的时候，才会考虑使用这个。
    
    （译者注：以上的配置可以选择继承AuthorizationServerConfigurerAdapter并且覆写其中的三个configure方法来进行配置。）
 
    
-    配置授权服务一个比较重要的方面就是提供一个授权码给一个OAuth客户端（通过 authorization_code 授权类型），一个授权码的获取是OAuth客户端跳转到一个授权页面，然后通过验证授权之后服务器重定向到OAuth客户端，并且在重定向连接中附带返回一个授权码



#### AuthorizationServerTokenServices 接口定义了一些操作使得你可以对令牌进行一些必要的管理，在使用这些操作的时候请注意以下几点：
        



#### JWT令牌 
     使用JWT令牌你需要在授权服务中使用 JwtTokenStore，资源服务器也需要一个解码的Token令牌的类 JwtAccessTokenConverter，JwtTokenStore依赖这个类来进行编码以及解码，因此你的授权服务以及资源服务都需要使用这个转换类。Token令牌默认是有签名的，并且资源服务需要验证这个签名，因此呢，你需要使用一个对称的Key值，用来参与签名计算，这个Key值存在于授权服务以及资源服务之中。或者你可以使用非对称加密算法来对Token进行签名，Public Key公布在/oauth/token_key这个URL连接中，默认的访问安全规则是"denyAll()"，即在默认的情况下它是关闭的，你可以注入一个标准的 SpEL 表达式到 AuthorizationServerSecurityConfigurer 这个配置中来将它开启（例如使用"permitAll()"来开启可能比较合适，因为它是一个公共密钥）。
     如果你要使用 JwtTokenStore，请务必把"spring-security-jwt"这个依赖加入到你的classpath中
    
    
    
### 资源服务器

        @EnableResourceServer 注解到一个 @Configuration 配置类上，并且必须使用 ResourceServerConfigurer 这个配置对象来进行配置（可以选择继承自 ResourceServerConfigurerAdapter 然后覆写其中的方法，参数就是这个对象的实例），下面是一些可以配置的属性：
        tokenServices：ResourceServerTokenServices 类的实例，用来实现令牌服务。
        resourceId：这个资源服务的ID，这个属性是可选的，但是推荐设置并在授权服务中进行验证。
        其他的拓展属性例如 tokenExtractor 令牌提取器用来提取请求中的令牌。
        请求匹配器，用来设置需要进行保护的资源路径，默认的情况下是受保护资源服务的全部路径。
        受保护资源的访问规则，默认的规则是简单的身份验证（plain authenticated）。
        其他的自定义权限保护规则通过 HttpSecurity 来进行配置    
