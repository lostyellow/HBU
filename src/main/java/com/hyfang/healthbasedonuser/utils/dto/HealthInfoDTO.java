package com.hyfang.healthbasedonuser.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthInfoDTO {
    @NotBlank
    private  String title;

    @NotBlank
    private  String content;

    @NotNull
    private  Integer categoryId;
}
