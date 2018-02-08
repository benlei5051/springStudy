package org.andy.beans.jackson.demo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andy.beans.jackson.entity.type.BaseClass;
import org.andy.beans.jackson.entity.type.Impl1;
import org.andy.beans.jackson.entity.type.Impl2;
import org.andy.beans.jackson.entity.type.PojoWithTypedObjects;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: andy
 * @Date: 2017/12/27 16:38
 * @Description:
 */
public class jacksonDemo2 {
    public static void main(String[] args) throws ParseException, IOException {
        Impl1 impl1=new Impl1();
        impl1.setX(25);
        Impl2 impl2=new Impl2();
        impl2.setName("linhao");
        PojoWithTypedObjects pojoWithTypedObjects=new PojoWithTypedObjects();
        List<BaseClass> list=new ArrayList<>();
        list.add(impl1);
        list.add(impl2);
        pojoWithTypedObjects.setItems(list);

        ObjectMapper mapper = new ObjectMapper();
        /* default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
          输出:{"items":["java.util.ArrayList",[["org.andy.beans.jackson.entity.type.Impl1",{"x":25}],["org.andy.beans.jackson.entity.type.Impl2",{"name":"linhao"}]]]} */
//       mapper.enableDefaultTyping();

        /* 输出:{"@class":"org.andy.beans.jackson.entity.type.PojoWithTypedObjects","items":["java.util.ArrayList",[{"@class":"org.andy.beans.jackson.entity.type.Impl1","x":25},{"@class":"org.andy.beans.jackson.entity.type.Impl2","name":"linhao"}]]} */
//        mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.NON_FINAL,"@class");

        /* 输出:{"@class":"org.andy.beans.jackson.entity.type.PojoWithTypedObjects","items":["java.util.ArrayList",[{"@class":"org.andy.beans.jackson.entity.type.Impl1","x":25},{"@class":"org.andy.beans.jackson.entity.type.Impl2","name":"linhao"}]]} */
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        /* BaseClass类上面申明注解后，
           输出:{"items":[{"@class":"org.andy.beans.jackson.entity.type.Impl1","x":25},{"@class":"org.andy.beans.jackson.entity.type.Impl2","name":"linhao"}]}*/
        String json = mapper.writeValueAsString(pojoWithTypedObjects);
        System.out.println(json);


    }
}
