package org.andy.beans.lombok;

/**
 * @author: andy
 * @Date: 2017/11/14 9:57
 * @Description:
 */
public class TestDemo {
    public static void main(String[] args){
        SysUser sysUser=SysUser.builder().id(10L).organizationId(200L).build();
        System.out.println(sysUser.toString());
    }
}
