package com.hyfang.healthbasedonuser.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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

}
