package design.patterns.builder;

/**
 * @author: andy
 * @Date: 2018/3/12 17:47
 * @Description:
 */
public class WomanBuilder implements PersonBuilder{

    private Person person;

    public WomanBuilder() {
        this.person = new Woman();
    }

    @Override
    public void buildHead() {
        person.setHead("建造女人的头");
    }

    @Override
    public void buildBody() {
        person.setBody("建造女人的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("建造女人的脚");
    }

    @Override
    public Person buildPerson() {
        return person;
    }
}
