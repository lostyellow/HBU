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
 * 健康信息
 * </p>
 *
 * @author hyfang
 * @since 2025-03-20
 */
@Getter
@Setter
@TableName("health_info")
@Schema(name = "HealthInfo", description = "健康信息")
public class HealthInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "富文本内容")
    private String content;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "作者id")
    private Integer authorId;

    @Schema(description = "审核状态：0-待审核 1-已通过 2-未通过")
    private Byte auditStatus;

    @Schema(description = "审核信息")
    private String auditRemark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
