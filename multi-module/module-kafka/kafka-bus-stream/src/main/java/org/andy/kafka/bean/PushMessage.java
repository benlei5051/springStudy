package org.andy.kafka.bean;

import java.io.Serializable;


public class PushMessage implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8008135763593983150L;


    private String title;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
