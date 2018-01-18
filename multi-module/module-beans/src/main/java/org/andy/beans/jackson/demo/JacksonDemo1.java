package org.andy.beans.jackson.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andy.beans.jackson.entity.User1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author: andy
 * @Date: 2017/12/27 15:56
 * @Description:
 */
public class JacksonDemo1 {
    public static void main(String[] args) throws ParseException, IOException {
        User1 user = new User1();
        user.setName("zhangsan");
        user.setEmail("zhangsan@163.com");
        user.setAge(20);

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        user.setBirthday(dateformat.parse("1996-10-01"));

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(user);
        System.out.println(json);
System.out.println("------------------");
        //如果反序列化的key于对象中的属性名称不一致会直接报错，故在需要反序列化的类上加上注解@JsonIgnoreProperties(ignoreUnknown = true)，可以解决这个问题
      //  String des="{\"birthday\":\"1996年09月30日\",\"my_email\":\"zhangsan@163.com\"}";
        String des="{\"birthday\":\"1996年09月30日\"}";
        User1 user1 = mapper.readValue(des, User1.class);
        System.out.println(user1.getEmail());
    }
}
