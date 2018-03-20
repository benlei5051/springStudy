package design.patterns.Flyweight;

/**
 * @author: andy
 * @Date: 2018/3/19 18:16
 * @Description:
 */
public class Clienter {
    public static void main(String[] args) {
        String yundong ="足球";
        for(int i = 1;i <= 5;i++){
            TiYuGuan tyg = JianZhuFactory.getTyg(yundong);
            tyg.setName("中国体育馆");
            tyg.setShape("圆形");
            tyg.use();
            System.out.println("对象池中对象数量为："+JianZhuFactory.getSize());
        }
    }
}
