package org.andy.beans.condition;

import org.springframework.stereotype.Component;

/**
 * @author: andy
 * @Date: 2017/9/26 15:50
 * @Description:
 */
@PoolOrClusterEnabled
@Component
public class InjectBean {
    public void eat(){
        System.out.println("今天吃什么");
    }
}
