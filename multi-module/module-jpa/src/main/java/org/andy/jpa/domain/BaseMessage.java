package org.andy.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import java.util.Date;

/**
 * @author: andy
 * @Date: 2017/11/2 12:55
 * @Description:
 */
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity(name = "message")
@DiscriminatorColumn(name="discriminator",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("system")
public class BaseMessage {

    /**
     * 消息Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 报警是否解除 0:未解除 1:已解除
     */
    @Column(name = "is_new")       //0:已读,1:未读
    private Integer isNew;

    /**
     * 消息内容
     */
    private String msg;


    /**
     * 消息产生时间
     */
    private Date time;


    /**
     * 消息标题
     */
    private String title;

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
