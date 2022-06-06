package com.liugui.springboot.req;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class PageReq {

    @NotNull(message = "页码不能为空")
    private int pageNum;

    @NotNull(message = "单页数量不能为空")
    @Max(value = 100,message = "单页数据量不能大于100条")
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
