package com.liugui.springboot.vo;

import java.util.List;

public class PageVo<T>{
    private int total;

    private  List<T> content;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageVo{" +
                "total=" + total +
                ", content=" + content +
                '}';
    }
}
