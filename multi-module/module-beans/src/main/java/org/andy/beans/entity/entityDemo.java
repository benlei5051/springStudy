package org.andy.beans.entity;

/**
 * @author: andy
 * @Date: 2017/11/14 9:57
 * @Description:
 */
public class entityDemo {
    public static void main(String[] args){
        SysUser sysUser=SysUser.builder().id(10l).organizationId(200l).build();
        System.out.println(sysUser.toString());
    }

}
