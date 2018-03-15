package design.patterns.builder;

/**
 * @author: andy
 * @Date: 2018/3/12 17:53
 * @Description:
 * 建造者模式：是将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 */
public class Test {
    public static void main(String[] args){
        PersonDirector personDirector = new PersonDirector();
        Person manPerson = personDirector.constructPerson(new ManBuilder());
        Person womanPerson = personDirector.constructPerson(new WomanBuilder());
    }
}
