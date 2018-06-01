##  使用redis作为缓存

- 三个主要的注解 Cacheable (最常用的注解，用于标注需要缓存方法)、
CacheEvict(用于仅清除缓存)、CachePut(用于仅存放缓存)

        
>   @Cacheable 主要用于查询上
        
        value、cacheNames：两个等同的参数，用于指定缓存存储的命名空间
        key：缓存对象存储在Redis的key值（每一个方法都配置），缺省按照函数的所有参数组合作为key值，若自己配置需使用SpEL表达式，比如：@Cacheable(key = "#p0")：使用函数第一个参数作为缓存的key值
        keyGenerator：用于指定key生成器（全局的），配置了参数key，就不能配置keyGenerator参数，若需要指定一个自定义的key生成器，我们需要去实现org.springframework.cache.interceptor.KeyGenerator接口，并使用该参数来指定。
        condition：缓存对象的条件，也需使用SpEL表达式，只有满足表达式条件的内容才会被缓存，比如：@Cacheable(key = "#p0", condition = "#p0.length() < 3")，表示只有当第一个参数的长度小于3的时候才会被缓存
        unless：另外一个缓存条件参数，需使用SpEL表达式。它不同于condition参数的地方在于它的判断时机，该条件是在函数被调用之后才做判断的，所以它可以通过对result进行判断。
        
>    @CacheEvict 主要用于删除方法上   

        allEntries：非必需，默认为false。当为true时，会移除所有数据
        beforeInvocation：非必需，默认为false，会在调用方法之后移除数据。当为true时，会在调用方法之前移除数据
        
>    @CachePut 主要用于新增和修改方法上