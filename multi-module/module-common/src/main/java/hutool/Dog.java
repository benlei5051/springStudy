package hutool;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;

/**
 * @author: andy
 * @Date: 2018/3/23 15:12
 * @Description:
 */
public class Dog extends CloneSupport<Dog> {
    private String name = "wangwang";
    private int age = 3;
}
