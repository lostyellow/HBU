package com.hyfang.healthbasedonuser.common;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class PageResultBean<T> {
    @Schema(description = "数据总条数")
    private Long total;
    @Schema(description = "当前页数据集合")
    private List<T> items;

    public PageResultBean() {
    }

    public PageResultBean(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public static <E> PageResultBean<E> getInstance(Long total, List<E> items) {
        return new PageResultBean<>(total, items);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
