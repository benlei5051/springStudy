package hutool;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.Cloneable;

/**
 * @author: andy
 * @Date: 2018/3/23 15:09
 * @Description:
 */
public  class Cat implements Cloneable<Cat> {
    private String name = "miaomiao";
    private int age = 2;

    @Override
    public Cat clone() {
        try {
            return (Cat) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneRuntimeException(e);
        }
    }
}
