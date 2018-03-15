package design.patterns.builder;

/**
 * @author: andy
 * @Date: 2018/3/12 17:51
 * @Description:
 * Director（调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。）
 */
public class PersonDirector {
    public Person constructPerson(PersonBuilder personBuilder) {
        personBuilder.buildHead();
        personBuilder.buildBody();
        personBuilder.buildFoot();
        return personBuilder.buildPerson();
    }
}
