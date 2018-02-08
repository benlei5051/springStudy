package org.andy.jpa.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;


/**
 * The persistent class for the message database table.
 */
@Entity
/*@SqlResultSetMapping(
        name = "messageMapping",
        entities = {
                @EntityResult(entityClass = Info.class)}
)*/

/*@SqlResultSetMapping(
        name = "messageMapping",
        columns = { @ColumnResult(name = "tt"), @ColumnResult(name = "message") }
)*/

@SqlResultSetMapping(
        name = "messageMapping",
        classes = {
                @ConstructorResult(targetClass = Info.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "msg", type = String.class),
                                @ColumnResult(name = "title", type = String.class)
                        })
        }
)
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息Id
     */
    @Id
    private Long id;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 消息标题
     */
    private String title;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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