package org.andy;

import org.andy.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: andy
 * @Date: 2017/9/13 17:20
 * @Description:
 */
public class LambdaDemo {
    public static void main(String[] args){
        LambdaDemo.test13();
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
        user.setAge(10);
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

    public static void test6(){
        String[] strings=new String[]{"abc","def","hhh",""};
        List<String> lists= Arrays.asList(strings);
        List<String> tests=new ArrayList<>();
        lists.forEach(str->{
            if (null != str && !str.isEmpty()){
                tests.add(str);
            }
        });
        tests.forEach(System.out::println);
    }
    public static void test7(){
        String[] strings=new String[]{"abc","def","hhh",""};
        List<String> lists= Arrays.asList(strings);
        List<String> tests = lists.stream().filter(s -> !s.equals("abc")).collect(Collectors.toList());
        tests.forEach(System.out::println);
    }
    //统计字母以d开头的单词有几个
    public static  void test8(){
        String[] strings=new String[]{"abc","def","hhh","dcc"};
        List<String> lists= Arrays.asList(strings);
        /*final Function<String,Predicate<String>> startsWithLetter=(String letter)->{
            Predicate<String> checkStartsWith=(String name)->name.startsWith(letter);
            return checkStartsWith;
        };*/
        //或者
        final  Function<String,Predicate<String>> startsWithLetter=letter->name->name.startsWith(letter);
        Long count=lists.stream().filter(startsWithLetter.apply("d")).count();
        System.out.println(count);
    }
    //查询第一个符合某种条件的元素
    public static void test9(){
        String[] strings=new String[]{"abc","def","hhh","dcc"};
        List<String> lists= Arrays.asList(strings);
        Optional<String> optional=lists.stream().filter(name->name.startsWith("e")).findFirst();
        System.out.println(optional.orElse("no Name"));
//        在调用filter后，调用了findFirst方法，这个方法返回的对象类型时Optional。关于这个Optional，
//        可以将它理解成一个可能存在，也可能不存在的结果。
//        这样的话，就可以避免对返回结果进行空检查了。对于结果是否真的存在，可以使用isPresent()方法进行判断，
//        而get()方法用于尝试对结果的获取。当结果不存在时，我们也可以使用orElse()来指定一个替代结果，正如上面使用的那样。
    }
    //排序，从小到达排序
    public static void test10(){
        Integer[] strings=new Integer[]{10,9,8,99};
        List<Integer> lists= Arrays.asList(strings);
   //     lists.sort((a,b)-> Integer.compare(a,b));
        lists.sort(Comparator.comparing((a)->a.intValue()));
        lists.forEach(System.out::println);
    }
    //查询集合中长度最长的字符串
    public static void test11(){
        String[] strings=new String[]{"abc","defe","hhheh","dccccc"};
        List<String> lists= Arrays.asList(strings);
        Optional<String> optional=lists.stream().reduce((a,b)->a.length()>=b.length() ? a : b);
        System.out.println(optional.orElse("no string"));
    }

    //字符串连接
    public static void test12(){
        String[] strings=new String[]{"abc","defe","hhheh","dccccc"};
        List<String> lists= Arrays.asList(strings);
        System.out.println(lists.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
    }
    public static void test13(){
        String str="8bbbc9c";
       // str.chars().mapToObj(ch->Character.valueOf((char)ch)).forEach(System.out::println);
        System.out.println("---------------------华丽的分割线");
        //判断是数字的字符串
        str.chars().filter(Character::isDigit).forEach(ch->System.out.println((char)ch));
    }

}
