package org.andy.authority;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author: andy
 * @Date: 2018/6/5 23:00
 * @Description:
 */
public class Demo {
    public static void main(String[] args){
        String abc = new Md5PasswordEncoder().encodePassword("abc", null);
        System.out.println(abc);
    }
}
