客户端信息配置在内存中

使用oauth2保护你的应用，可以分为简易的分为三个步骤
配置资源服务器
配置认证服务器
配置spring security


OAUTH2中的角色：

Resource Server:被授权访问的资源
Authotization Server：OAUTH2认证授权中心
Resource Owner： 用户
Client：使用API的客户端(如Android 、IOS、web app)






Authorization Code:用在服务端应用之间, 授权码模式使用到了回调地址，是最为复杂的方式，
通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。

Implicit:用在移动app或者web app(这些app是在用户的设备上的，如在手机上调起微信来进行认证授权),简化模式不常用。

Resource Owner Password Credentials(password):应用直接都是受信任的(都是由一家公司开发的，本例子使用)

Client Credentials:用在应用API访问。

启动springboot应用就可以发现多了一些自动创建的endpoints：


{[/oauth/authorize]}
{[/oauth/authorize],methods=[POST]
{[/oauth/token],methods=[GET]}
{[/oauth/token],methods=[POST]}
{[/oauth/check_token]}
{[/oauth/error]}


本例：

采用password模式
用户名和密码模拟存入数据库中
客户端的id和密码存入内存中
token存入redis中

     //允许所有用户访问"/"和"/home"
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    //其他地址的访问均需验证权限
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    //指定登录页是"/login"
                    .loginPage("/login")
                    .defaultSuccessUrl("/hello")//登录成功后默认跳转到"/hello"
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/home")//退出登录后的默认url是"/home"
                    .permitAll();






