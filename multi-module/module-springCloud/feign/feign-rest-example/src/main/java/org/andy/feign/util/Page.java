package org.andy.feign.util;

public class Page {

    private Integer size;// 分页相关:每页大小
    private Integer number;// 分页相关：当前页码
    private Integer totalElements;//分页相关:总条数
    private Integer totalPages;//分页相关：一共多少页

    public Page() {
        super();
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
