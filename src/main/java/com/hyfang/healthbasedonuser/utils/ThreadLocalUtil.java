package com.hyfang.healthbasedonuser.utils;

public class ThreadLocalUtil {
    // 提供ThreadLocal 对象
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<>();

    // 获取存储值
    public static <T> T get() {
        return (T) THREAD_LOCAL.get();
    }

    // 存储值
    public static void set(Object value) {
        THREAD_LOCAL.set(value);
    }

    // 清除THREAD_LOCAL 防止内存泄漏
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
