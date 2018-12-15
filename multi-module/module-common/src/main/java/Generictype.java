public class Generictype {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        System.out.println("1-----------");
        Dog dog1 = creatNew1(Dog.class);
        System.out.println(dog1.name + " is " + dog1.type + "\n");

        System.out.println("2-----------");
        Animal ani1 = creatNew2(Cat.class);
        Cat cat1 = (Cat) ani1;
        System.out.println(cat1.name + " is " + cat1.type + "\n");

        System.out.println("3-----------");
        Object obj = creatNew3(Dog.class);
        Dog dog2 = (Dog) obj;
        System.out.println(dog1.name + " is " + dog2.type + "\n");

        System.out.println("4-----------");
        Object obj2 = creatNew4(Cat.class);
        Cat cat2 = (Cat) obj2;
        System.out.println(cat2.name + " is " + cat2.type + "\n");

        System.out.println("5-----------");
        Object obj3 = creatNew5(new Cat());
        Cat cat3 = (Cat) obj3;
        System.out.println(cat3.name + " is " + cat3.type + "\n");
    }

    /**
     * 此方法是一个非泛型类中的泛型方法，参数为Class<T>类型，可以传入任何类，但是Class<T>将参数在函数内部的类型固定为 T类，使用clazz.newInstance(),返回的类型也为固定的 T 类型。
     * 如: 传入Dog.class, 函数中的 T 固定为Dog.class,函数返回的是Dog类型，不需要强制转换为Dog类型
     * （当然，函数返回类型也可以是Object类型，但是没必要）
     */
    public static <T> T creatNew1(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        System.out.println(clazz);//打印具体传入的类型。
        return clazz.newInstance();
    }

    /**
     * 此方法参数是Class<? extends Animal>，只能传入Animal及其子类的类型，函数返回类型只能声明为Animal或Object类型，
     * 如：传入Cat.class，函数返回一个Animal的子类对象，需将此子类对象强制转换为具体的子类(即Cat类)。
     */
    public static Animal creatNew2(Class<? extends Animal> clazz) throws IllegalAccessException, InstantiationException {
        System.out.println(clazz);//打印具体传入的类型。
        return clazz.newInstance();
    }

    /**
     * 此方法参数是Class，可传入任何类，其返回只能声明为Object类对象。
     * 如：传入Dog.class, 返回的是Object类型的对象，需强制转换为Dog类型的对象。
     */
    public static Object creatNew3(Class clazz) throws IllegalAccessException, InstantiationException {
        System.out.println(clazz);//打印具体传入的类型。
        return clazz.newInstance();
    }

    /**
     * 传入参数Class<?> 和 上一个方法传入 Class 的区别还不是很清楚，其返回只能声明为Object类对象。
     * 如：Cat.class, 返回的是Object类型的对象，需强制转换为Cat类型的对象。
     */
    public static Object creatNew4(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        System.out.println(clazz);//打印具体传入的类型。
        return clazz.newInstance();
    }

    /**
     * 传入Object类型的对象，返回也声明为Object类型。
     */
    public static Object creatNew5(Object object) {
        System.out.println(object.getClass());//打印具体传入的类型。
        return object;
    }
}

abstract class Animal {
    public String type = "type = animal ";
}

class Dog extends Animal {
    public String name = "name = gou";
}

class Cat extends Animal {
    public String name = "name = mao";
}

/**
 * 这些方法，传的参数声明不同，其返回的声明也必然要随之不同。
 */