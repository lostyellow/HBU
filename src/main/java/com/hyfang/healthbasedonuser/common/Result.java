package com.hyfang.healthbasedonuser.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result<T> {
    @Schema(description = "业务状态码 0：成功  1: 失败")
    private int code;        // 业务状态码 0：成功  1: 失败
    @Schema(description = "提示信息")
    private String message;  // 提示信息
    @Schema(description = "返回数据")
    private T data;          // 响应数据

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 操作成功返回响应结果（带响应数据）
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <E> Result<E> success() {
        return new Result<>(0, "操作成功", null);
    }

    public static <E> Result<E> error(String message) {
        return new Result<>(1, message, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
