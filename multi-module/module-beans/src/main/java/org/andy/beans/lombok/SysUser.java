package org.andy.beans.lombok;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 @Data 标签，生成getter/setter toString() equals()等方法
 @NonNull : 让你不在担忧并且爱上NullPointerException
 @CleanUp : 自动资源管理：不用再在finally中添加资源的close方法
 @Setter/@Getter : 自动生成set和get方法
 @ToString : 自动生成toString方法
 @EqualsAndHashcode : 从对象的字段中生成hashCode和equals的实现
 @NoArgsConstructor/@RequiredArgsConstructor/@AllArgsConstructor
 自动生成构造方法
 @Data : 自动生成set/get方法，toString方法，equals方法，hashCode方法，不带参数的构造方法
 @Value : 用于注解final类
 @Builder : 产生复杂的构建器api类
 @SneakyThrows : 异常处理（谨慎使用）
 @Synchronized : 同步方法安全的转化
 @Getter(lazy=true) :
 @Log : 支持各种logger对象，使用时用对应的注解，如：@Log4j
 */


/*关于lombok会导致ENTITY-FK调用陷入死循环的解决方案：
        1. 使用@Getter @Setter @ToString代替@Data并且自定义equals(Object other) 和 hashCode()方法，比如有些类只需要判断主键id是否相等即足矣。
        2. 或者使用在使用@Data时同时加上@EqualsAndHashCode(callSuper=true)注解。

        这三个@Getter @Setter @ToString代替@Data，不会自动重写equals和hashcode*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    private Long id;

    private Long organizationId;

    private String userName;

    private String password;

    private String salt;

    private Long roleId;

    private String realName;

    private String phone;

    private String email;

    private Boolean locked;

    private String token;

    private Long iamId;

    private Integer flag;


}
