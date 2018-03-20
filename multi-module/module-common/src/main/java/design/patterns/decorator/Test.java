package design.patterns.decorator;

/**
 * @author: andy
 * @Date: 2018/3/13 11:10
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Humburger humburger = new ChickenBurger();
        System.out.println(humburger.getName() + " 价钱: " + humburger.getPrice());
        Lettuce lettuce = new Lettuce(humburger);
        System.out.println(lettuce.getName() + " 价钱: " + lettuce.getPrice());
        Chilli chilli = new Chilli(humburger);
        System.out.println(chilli.getName() + " 价钱: " + chilli.getPrice());
        Chilli chilli12 = new Chilli(lettuce);
        System.out.println(chilli12.getName() + " 价钱: " + chilli12.getPrice());

    }
}
