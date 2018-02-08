package org.andy.jpa.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;


public class InfoModel implements Serializable {


    private static final long serialVersionUID = -8446045079724998425L;
    /**
     * 消息内容
     */
    private String msg;

    /**
     * 消息标题
     */
    private String title;

    public InfoModel() {
    }



    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}