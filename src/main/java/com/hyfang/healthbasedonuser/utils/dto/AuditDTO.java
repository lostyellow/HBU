package com.hyfang.healthbasedonuser.utils.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class AuditDTO {
    @NotNull
    @Range(min = 1, max = 2)
    private Integer status;

    @Size(max = 200)
    private String remark;
}
