package com.hyfang.healthbasedonuser.interceptors;

import com.hyfang.healthbasedonuser.utils.JwtUtil;
import com.hyfang.healthbasedonuser.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = request.getHeader("Authorization");
        // 验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 存储业务信息到线程变量
            ThreadLocalUtil.set(claims);
            // 放行拦截
            return true;
        } catch (Exception e) {
            // 登录信息异常或未登录，http响应状态码为401
            response.setStatus(401);
            // 拦截请求
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除ThreadLocal业务数据
        ThreadLocalUtil.remove();
    }
}
