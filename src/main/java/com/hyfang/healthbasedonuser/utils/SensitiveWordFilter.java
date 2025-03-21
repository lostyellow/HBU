package com.hyfang.healthbasedonuser.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SensitiveWordFilter {
    private final Set<String> sensitiveWords = new HashSet<>();

    @PostConstruct
    public void init() {
        // 加载敏感词库
    }

    public String filter(String text) {
        // 实现替换逻辑
//        return processedText;
        return null;
    }

    public boolean containsSensitiveWord(String content) {
        return sensitiveWords.contains(content);
    }
}
