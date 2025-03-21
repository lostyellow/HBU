package com.hyfang.healthbasedonuser.common;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class PageQuery {
    /**
     * 当前页码（从1开始）
     */
    @Schema(description = "页码（从1开始）", example = "1")
    @Min(value = 1, message = "页码最小为1")
    private Integer page = 1;

    /**
     * 每页数量
     */
    @Schema(description = "每页数量", example = "10")
    @Min(value = 1, message = "每页数量最小为1")
    @Max(value = 100, message = "每页数量最大为100")
    private Integer size = 10;

    /**
     * 排序字段（格式：字段名,asc/desc）
     */
    @Schema(description = "排序字段（格式：字段名,asc/desc）", example = "createTime,desc")
    private String sort;


    public <T> Page<T> toPage() {
        Page<T> page = new Page<>(this.page, this.size);
        if (StringUtils.hasText(sort)) {
            String[] sortParams = sort.split(",");
            if (sortParams.length == 2) {
                // 使用新的构造方式
                page.addOrder(OrderItem.desc(sortParams[0]));
                // 或者根据排序方向动态设置
                if ("desc".equalsIgnoreCase(sortParams[1])) {
                    page.addOrder(OrderItem.desc(sortParams[0]));
                } else {
                    page.addOrder(OrderItem.asc(sortParams[0]));
                }
            }
        }
        return page;
    }
}
