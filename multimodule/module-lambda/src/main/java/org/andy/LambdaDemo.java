package org.andy;

import org.andy.entity.Person;
import org.andy.entity.Student;
import org.andy.entity.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author: andy
 * @Date: 2017/9/13 17:20
 * @Description:
 */
public class LambdaDemo {
    private static List<Student> students = new ArrayList<Student>();
    static {
        students.add(new Student(20160001, "孔明", 20, 1, "土木工程", "武汉大学"));
        students.add(new Student(20160002, "伯约", 21, 2, "信息安全", "武汉大学"));
        students.add(new Student(20160003, "玄德", 22, 3, "经济管理", "武汉大学"));
        students.add(new Student(20160004, "云长", 21, 2, "信息安全", "武汉大学"));
        students.add(new Student(20161001, "翼德", 21, 2, "机械与自动化", "华中科技大学"));
        students.add(new Student(20161002, "元直", 23, 4, "土木工程", "华中科技大学"));
        students.add(new Student(20161003, "奉孝", 23, 4, "计算机科学", "华中科技大学"));
        students.add(new Student(20162001, "仲谋", 22, 3, "土木工程", "浙江大学"));
        students.add(new Student(20162002, "鲁肃", 23, 4, "计算机科学", "浙江大学"));
        students.add(new Student(20163001, "丁奉", 24, 5, "土木工程", "南京大学"));
    }
    public static void main(String[] args) throws  Exception{
/*        List<Person> list=new ArrayList<>();
        Person person=new Person("AA",28);
        list.add(person);
        OptionalInt maxAge = list.stream()
                .mapToInt(Person::getAge)
                .max();
        System.out.println(maxAge.orElse(0));*/
      LambdaDemo.lam3();
    }

    /**
     * filter用法
     * filter接受一个谓词Predicate，我们可以通过这个谓词定义筛选条件，
     * 在介绍lambda表达式时我们介绍过Predicate是一个函数式接口，其包含一个test(T t)方法，该方法返回boolean。
     *
     * 实际上对于顺序流式处理而言，findFirst和findAny返回的结果是一样的，至于为什么会这样设计，
     * 是因为在下一篇我们介绍的并行流式处理，当我们启用并行流式处理的时候，
     * 查找第一个元素往往会有很多限制，如果不是特别需求，在并行流式处理中使用findAny的性能要比findFirst好。
     */
    public static void lam1(){
        List<Student> studentList=LambdaDemo.students.stream().filter(student -> "武汉大学".equals(student.getSchool())).collect(Collectors.toList());
        studentList.forEach(System.out::println);
    }

    /**
     * distinct用法
     * distinct操作类似于我们在写SQL语句时，添加的DISTINCT关键字，用于去重处理，
     */
    public static void lam2(){
        List<Integer> nums=Arrays.asList(2,4,6,7,4,8);
        List<Integer> evens = nums.stream()
                .filter(num -> num % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        evens.forEach(System.out::println);
    }

    /**
     * limit 方法用于获取指定数量的流。 以下代码片段使用 limit 方法打印出 10 条数据：
     *
     * limit操作也类似于SQL语句中的LIMIT关键字，不过相对功能较弱，
     * limit返回包含前n个元素的流，当集合大小小于n时，则返回实际长度，比如下面的例子返回前两个专业为土木工程专业的学生：
     *
     *
     */
    public static void lam3(){
        Random random=new Random();
        random.ints(10,30).limit(10).forEach(System.out::println);

        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).limit(2)
                .forEach(System.out::println);
    }

    /**
     * sort
     *
     *
     * orted要求待比较的元素必须实现Comparable接口，如果没有实现也不要紧，
     * 我们可以将比较器作为参数传递给sorted(Comparator<? super T> comparator)，
     * 比如我们希望筛选出专业为土木工程的学生，并按年龄从小到大排序，筛选出年龄最小的两个学生，那么可以实现为：
     */

    public static void lam4(){
        students.stream()
                .filter(student -> "土木工程".equals(student.getMajor())).sorted((s1, s2) -> s1.getAge() - s2.getAge())
                .limit(2)
                .forEach(System.out::println);
    }

    //排序
    public static void test14(){
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));
//        List<Person> ascendingAge=people.stream().sorted(Person::ageDifference).collect(Collectors.toList());
//        ascendingAge.forEach(System.out::println);
        //降序排列
        Comparator<Person> compareAscending = (person1, person2) -> person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();
        people.stream().sorted(compareDescending).forEach(System.out::println);
    }

    /**
     * map
     *
     *
     */
    public static  void lam5(){
        List<String> names = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
    }

    /**
     * 筛选出所有专业为计算机科学的学生姓名，那么我们可以在filter筛选的基础之上，通过map将学生实体映射成为学生姓名字符串
     */
    public static  void lam6(){
        int totalAge = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();
        System.out.println(totalAge+"------总年龄");
    }
    /**
     * flatmap
     *flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流
     *
     *
     * 假设我们有一个字符串数组String[] strs = {"java8", "is", "easy", "to", "use"};，我们希望输出构成这一数组的所有非重复字符
     */
    public static void lam7(){
        String[] strs = {"java8", "is", "easy", "to", "use"};
        //错误，达不到期望的目的
      /*  List<String[]> distinctStrs = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .distinct()
                .collect(Collectors.toList());*/

      //flatMap将由map映射得到的Stream<String[]>，转换成由各个字符串数组映射成的流Stream<String>，
        // 再将这些小的流扁平化成为一个由所有字符串构成的大流Steam<String>，从而能够达到我们的目的。
      //  与map类似，flatMap也提供了针对特定类型的映射操作：flatMapToDouble(Function<? super T,? extends DoubleStream> mapper)，
        // flatMapToInt(Function<? super T,? extends IntStream> mapper)，
        // flatMapToLong(Function<? super T,? extends LongStream> mapper)。
        //String[] childArray={"j","a","v","a"}
        Arrays.stream(strs).map(str->str.split("")).flatMap(childArray-> Arrays.stream(childArray)).forEach(System.out::println);
       /* List<String> collect = Arrays.stream(strs)
                .map(str -> str.split("")) //映射成Stream<String[]>
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        collect.forEach(System.out::println);*/
       /* Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);*/
        //flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字。

    }

    /**
     * allmatch
     * allMatch用于检测是否全部都满足指定的参数行为，如果全部满足则返回true，例如我们希望检测是否所有的学生都已满18周岁，那么可以实现为：
     */
    public static  void lam8(){
        boolean isAdult = students.stream().allMatch(student -> student.getAge() >= 18);
        System.out.println(isAdult);
    }

    /**
     * anyMatch
      *anyMatch则是检测是否存在一个或多个满足指定的参数行为，
     * 如果满足则返回true，例如我们希望检测是否有来自武汉大学的学生，那么可以实现为
     */
    public static void lam9(){
        boolean hasWhu = students.stream().anyMatch(student -> "武汉大学".equals(student.getSchool()));
        System.out.println(hasWhu);
    }

    /**
     * noneMathch
     * noneMatch用于检测是否不存在满足指定行为的元素，
     * 如果不存在则返回true，例如我们希望检测是否不存在专业为计算机科学的学生，可以实现如下：
     */
    public static void lam10(){
        boolean noneCs = students.stream().noneMatch(student -> "计算机科学".equals(student.getMajor()));
        System.out.println(noneCs);
    }

    /**
     * findFirst
     * findFirst用于返回满足条件的第一个元素，比如我们希望选出专业为土木工程的排在第一个学生，那么可以实现如下：
     */
    public static void lam11(){
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findFirst();
        System.out.println(optStu);
    }

    /**
     * findAny
     * findAny相对于findFirst的区别在于，findAny不一定返回第一个，而是返回任意一个，比如我们希望返回任意一个专业为土木工程的学生，可以实现如下
     */
    public static void lam12(){
        Optional<Student> optStu = students.stream().filter(student -> "土木工程".equals(student.getMajor())).findAny();
        System.out.println(optStu);
    }
    //终端操作是流式处理的最后一步，我们可以在终端操作中实现对流查找、归约等操作。

    /**
     * 面的例子中我们大部分都是通过collect(Collectors.toList())对数据封装返回，
     * 如我的目标不是返回一个新的集合，而是希望对经过参数化操作后的集合进行进一步的运算，
     * 那么我们可用对集合实施归约操作。java8的流式处理提供了reduce方法来达到这一目的。
     * 前面我们通过mapToInt将Stream<Student>映射成为IntStream，
     * 并通过IntStream的sum方法求得所有学生的年龄之和，实际上我们通过归约操作，也可以达到这一目的，实现如下：
     */
    public static void lam13(){
// 前面例子中的方法
        int totalAge1 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .mapToInt(Student::getAge).sum();
// 归约操作
        int totalAge2 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, (a, b) -> a + b);

// 进一步简化
        int totalAge3 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(0, Integer::sum);

// 采用无初始值的重载版本，需要注意返回Optional
        Optional<Integer> totalAge4 = students.stream()
                .filter(student -> "计算机科学".equals(student.getMajor()))
                .map(Student::getAge)
                .reduce(Integer::sum);  // 去掉初始值
        System.out.println("总的年龄:"+totalAge1+"---------"+totalAge2+"-----------"+totalAge3+"------------"+totalAge4);
    }

    /**
     * collect收集
     * 前面利用collect(Collectors.toList())是一个简单的收集操作，是对处理结果的封装，对应的还有toSet、toMap，
     * 以满足我们对于结果组织的需求。这些方法均来自于java.util.stream.Collectors，我们可以称之为收集器
     *
     * 收集器也提供了相应的归约操作，但是与reduce在内部实现上是有区别的，
     * 收集器更加适用于可变容器上的归约操作，这些收集器广义上均基于Collectors.reducing()实现。
     *
     */
    public static void lam14(){
        long count1 = students.stream().collect(Collectors.counting());

        // 进一步简化
        long count2 = students.stream().count();
        System.out.println(count1+"-----------"+count2);
    }

    /**
     * 求年龄的最大值和最小值
     */
    public static  void lam15(){

// 求最大年龄
      Optional<Student> olderStudent = students.stream().collect(Collectors.maxBy((s1, s2) -> s1.getAge() - s2.getAge()));
//        Student collect = students.stream().collect(Collectors.reducing(null, BinaryOperator.maxBy(Comparator.comparing(Student::getAge))));

// 进一步简化
        Optional<Student> olderStudent2 = students.stream().collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));

// 求最小年龄
        Optional<Student> olderStudent3 = students.stream().collect(Collectors.minBy(Comparator.comparing(Student::getAge)));

// 求年龄总和,对应的还有summingLong、summingDouble。
        int totalAge4 = students.stream().collect(Collectors.summingInt(Student::getAge));

// 求年龄的平均值,对应的还有averagingLong、averagingDouble。
        double avgAge = students.stream().collect(Collectors.averagingInt(Student::getAge));
//

// 一次性得到元素个数、总和、均值、最大值、最小值,对应的还有summarizingLong、summarizingDouble。
        IntSummaryStatistics statistics = students.stream().collect(Collectors.summarizingInt(Student::getAge));

        String names1 = students.stream().map(Student::getName).collect(Collectors.joining());
// 输出：孔明伯约玄德云长翼德元直奉孝仲谋鲁肃丁奉
        String names2 = students.stream().map(Student::getName).collect(Collectors.joining(", "));
// 输出：孔明, 伯约, 玄德, 云长, 翼德, 元直, 奉孝, 仲谋, 鲁肃, 丁奉

        System.out.println(olderStudent+"-----------"+olderStudent2+"----------------"+olderStudent3);
        System.out.println(totalAge4+"------"+avgAge);
        System.out.println(statistics);
        System.out.println(names1+"------------"+names2);
    }

    /**
     * 分组统计
     * 在数据库操作中，我们可以通过GROUP BY关键字对查询到的数据进行分组，
     * java8的流式处理也为我们提供了这样的功能Collectors.groupingBy来操作集合。比如我们可以按学校对上面的学生进行分组：
     */
    public static void lam16(){

        Map<String, Student> collect = students.stream().collect(Collectors.groupingBy(Student::getSchool,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Student::getAge)),
                        Optional::get
                )));


        Map<String, List<Student>> groups = students.stream().collect(Collectors.groupingBy(Student::getSchool));
        System.out.println(groups);
//   groupingBy接收一个分类器Function<? super T, ? extends K> classifier，我们可以自定义分类器来实现需要的分类效果。
//   上面演示的是一级分组，我们还可以定义多个分类器实现 多级分组，比如我们希望在按学校分组的基础之上再按照专业进行分组，实现如下：
        Map<String, Map<String, List<Student>>> groups2 = students.stream().collect(
                Collectors.groupingBy(Student::getSchool,  // 一级分组，按学校
                        Collectors.groupingBy(Student::getMajor)));  // 二级分组，按专业
        System.out.println(groups2);
//实际上在groupingBy的第二个参数不是只能传递groupingBy，
// 还可以传递任意Collector类型，比如我们可以传递一个Collector.counting，用以统计每个组的个数：
        Map<String, Long> group3 = students.stream().collect(Collectors.groupingBy(Student::getSchool, Collectors.counting()));
        System.out.println(group3);
    }

    /**
     * 分区
     * 分区可以看做是分组的一种特殊情况，在分区中key只有两种情况：true或false，
     * 目的是将待分区集合按照条件一分为二，java8的流式处理利用ollectors.partitioningBy()方法实现分区，
     * 该方法接收一个谓词，例如我们希望将学生分为武大学生和非武大学生，那么可以实现如下：
     *
     * 分区相对分组的优势在于，我们可以同时得到两类结果，
     * 在一些应用场景下可以一步得到我们需要的所有结果，比如将数组分为奇数和偶数。
     */
    public static void lam17(){
        Map<Boolean, List<Student>> partition = students.stream().collect(Collectors.partitioningBy(student -> "武汉大学".equals(student.getSchool())));
        System.out.println(partition);
    }



    /**
     * 根据年龄聚合所有的姓名 key 是年龄，value 是姓名集合
     */
    public static  void test17(){
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));
        //groupingBy方法参数第一个作为分类器，第二个作为对分类结果进行进一步操作的collector。
        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(nameOfPeopleByAge);

    }
    //根据名字的首字母进行分类，分类结果是名字以该首字母起头的年龄最大的人
    public static void test18(){
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonInEachAlphabet = people.stream()
                .collect(Collectors.groupingBy(person -> person.getName().charAt(0), Collectors.reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person in each alphabet: " + oldestPersonInEachAlphabet.get('S'));
        //以上的groupingBy方法的第二个参数执行了归约(Reduction)操作，而不是之前的映射(Mapping)操作。
        // 并且利用了BinaryOperator中定义的静态方法maxBy。
        // 在归约过程中，每次都会取参与的两个元素中较大的那个。最后就得到了整个集合中最大的那个元素。
    }








    /**
     * 处理空指针异常
     */
    public static void test1(){
    /*    if(null == str) {
            return 0;
        }
        return str.length();*/
        String a="ttt";
        Optional<Integer> s=Optional.ofNullable(a).map(String::length);
        System.out.println(s);
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


    /**
     * 最大最小值
     */
    public static void test15(){
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));
        people.stream().min(Person::ageDifference).ifPresent(System.out::println);
        people.stream().min(Person::ageDifference);
        people.stream().max((person1,person2)->person1.getAge()-person2.getAge()).ifPresent(System.out::println);
    }

    /**
     * 按名字排序
     */
    public static  void test16(){
        //将比较的属性抽取出函数接口
        final Function<Person, String> byName = person -> person.getName();
        final List<Person> people = Arrays.asList(
                new Person("John", 20),
                new Person("Sara", 21),
                new Person("Jane", 21),
                new Person("Greg", 35));
        people.stream().sorted(Comparator.comparing(byName)).collect(Collectors.toList()).forEach(System.out::println);
    }


    public static void test19() throws IOException {
        Files.newDirectoryStream(
                Paths.get("D:"+ File.separator+"dbeaver"),
                path -> path.toString().endsWith(".ini"))
                .forEach(System.out::println);
    }
    public static  void test20() throws IOException {
        Files.list(Paths.get("D:"+ File.separator+"java")).forEach(System.out::println);
    }


}
