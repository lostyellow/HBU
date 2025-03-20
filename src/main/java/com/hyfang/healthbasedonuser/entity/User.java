package com.hyfang.healthbasedonuser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hyfang
 * @since 2025-03-16
 */
@Setter
@Getter
@Schema(name = "User", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id，非空，主键，自动递增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户名，非空")
    private String loginName;

    @Schema(description = "密码，非空")
    private String password;

    @Schema(description = "姓名，非空")
    private String name;

    @Schema(description = "性别，非空，男1，女0")
    private String gender;

    @Schema(description = "联系电话")
    private String phone;

    @Override
    public String toString() {
        return "User{" +
            "id = " + id +
            ", loginName = " + loginName +
            ", password = " + password +
            ", name = " + name +
            ", gender = " + gender +
            ", phone = " + phone +
        "}";
    }
}
