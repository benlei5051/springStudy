package design.patterns.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: andy
 * @Date: 2018/3/19 18:15
 * @Description:
 */
public class JianZhuFactory {
    private static final Map<String,TiYuGuan> tygs = new HashMap<String,TiYuGuan>();
    public static TiYuGuan getTyg(String yundong){
        TiYuGuan tyg = tygs.get(yundong);
        if(tyg == null){
            tyg = new TiYuGuan(yundong);
            tygs.put(yundong,tyg);
        }
        return tyg;
    }
    public static int getSize(){
        return tygs.size();
    }
}
