package org.andy.beans.powermock.enumdemo;

/**
 * ==========================================================================
 * PersonType
 * @Description: TODO
 * @author: haolin@pateo.com.cn
 * @date: 2019/5/16 18:20
 * @version V1.0
 * @Copyright 博泰悦臻网络技术服务有限公司
 * ==========================================================================
 */
public enum  PersonType {
    S("student"), N("normal");

    private String type;

    PersonType(String type) {

        this.type = type;
    }

    public String getType(){
        return type;
    }
}
