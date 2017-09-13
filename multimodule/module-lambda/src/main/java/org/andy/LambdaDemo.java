package org.andy;

import org.andy.entity.User;

import java.util.Optional;

/**
 * @author: andy
 * @Date: 2017/9/13 17:20
 * @Description:
 */
public class LambdaDemo {
    public static void main(String[] args){
        LambdaDemo.test5();
    }

    /**
     * 处理空指针异常
     */
    public static void test1(){
    /*    if(null == str) {
            return 0;
        }
        return str.length();*/
        String a=null;
        System.out.println(Optional.ofNullable(a).map(String::length).orElse(0));
    }

    public static void test2(){
        //如果不能确定传入的参数是否存在null值的可能性，则可以用Optional的ofNullable()方法创建对象，如果入参为null，则创建一个空对象。
        User user=null;
        System.out.println(Optional.ofNullable(user).map(User::getName).orElse("no name"));
    }
    public static void test3(){
        //得到phone或email，利用上面的方式则行不通了，因为map之后返回的是Optional，我们把这种称为Optional嵌套，我们必须在map一次才能拿到我们想要的结果
        User user=null;
        Optional<User> optUser=Optional.ofNullable(user);
        //System.out.println(optUser.map(User::getPhone).map(Optional::get).orElse(-1L));
        System.out.println(optUser.flatMap(User::getPhone).orElse(-1L));
    }
    public static void test4(){
        User user=new User();
        user.setAge(22);
        //过滤以后，如果有满足的user（ifPresent）,则打印
        Optional<User> optUser=Optional.ofNullable(user);
        //ifPresent(Consumer<? super T>)当满足条件时执行传入的参数化操作
        optUser.filter(user1 -> user1.getAge()>=18).ifPresent(user2 -> System.out.println("Audit:"+user2));

    }
    public static  void test5(){
        User user=null;
        Optional<User> optUser=Optional.ofNullable(user);
        System.out.println(optUser.flatMap(User::getPhone).orElseGet(()->{
            return -6L;
        }));
    }
}
