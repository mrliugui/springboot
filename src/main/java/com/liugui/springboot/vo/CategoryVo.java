package com.liugui.springboot.vo;

public class CategoryVo {

    private Long id;

    private String name;

    private Long parent;

    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
    return "CategoryVo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", parent=" + parent +
            ", sort=" + sort +
            '}';
}
}