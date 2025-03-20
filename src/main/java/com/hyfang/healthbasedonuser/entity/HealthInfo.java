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
@TableName("health_info")
@Schema(name = "HealthInfo", description = "")
public class HealthInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private Integer categoryId;

    private Integer authorId;

    private Byte auditStatus;

    private String auditRemark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "HealthInfo{" +
            "id = " + id +
            ", title = " + title +
            ", content = " + content +
            ", categoryId = " + categoryId +
            ", authorId = " + authorId +
            ", auditStatus = " + auditStatus +
            ", auditRemark = " + auditRemark +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
        "}";
    }
}
