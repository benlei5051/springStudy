package org.andy.jackson.test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.andy.jackson.entity.User1;
import org.andy.jackson.entity.type.BaseClass;
import org.andy.jackson.entity.type.Impl1;
import org.andy.jackson.entity.type.Impl2;
import org.andy.jackson.entity.type.PojoWithTypedObjects;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
      //  mapper.enableDefaultTyping(); // default to using DefaultTyping.OBJECT_AND_NON_CONCRETE
       //["org.andy.jackson.entity.type.PojoWithTypedObjects",{"items":["java.util.ArrayList",[["org.andy.jackson.entity.type.Impl1",{"x":25}],["org.andy.jackson.entity.type.Impl2",{"name":"linhao"}]]]}]
       // mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.NON_FINAL,"@class");
      //  mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        String json = mapper.writeValueAsString(pojoWithTypedObjects);
        System.out.println(json);


    }
}
