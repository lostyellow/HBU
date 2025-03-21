package com.hyfang.healthbasedonuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 健康信息分类
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
@Getter
@Setter
@TableName("health_category")
@Schema(name = "HealthCategory", description = "健康信息分类")
public class HealthCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "分类描述")
    private String description;

    private LocalDateTime createTime;
}
