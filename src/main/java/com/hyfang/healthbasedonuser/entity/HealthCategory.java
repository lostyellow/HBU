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
 * 
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
@Setter
@Getter
@TableName("health_category")
@Schema(name = "HealthCategory", description = "")
public class HealthCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;

    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "HealthCategory{" +
            "id = " + id +
            ", name = " + name +
            ", description = " + description +
            ", createTime = " + createTime +
        "}";
    }
}
