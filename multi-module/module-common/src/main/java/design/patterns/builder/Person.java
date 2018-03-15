package design.patterns.builder;

/**
 * @author: andy
 * @Date: 2018/3/12 17:44
 * @Description:
 * Product（要创建的复杂对象。）
 */
public class Person {
    private String head;
    private String body;
    private String foot;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFoot() {
        return foot;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }
}
