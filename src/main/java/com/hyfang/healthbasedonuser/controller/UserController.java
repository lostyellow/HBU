package com.hyfang.healthbasedonuser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyfang.healthbasedonuser.common.PageResultBean;
import com.hyfang.healthbasedonuser.common.Result;
import com.hyfang.healthbasedonuser.entity.User;
import com.hyfang.healthbasedonuser.service.UserService;
import com.hyfang.healthbasedonuser.utils.JwtUtil;
import com.hyfang.healthbasedonuser.utils.Md5Util;
import com.hyfang.healthbasedonuser.utils.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyfang
 * @since 2025-03-16
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户列表分页
     * @param pageSize 每页显示的条数
     * @param currentPage  要查询的页
     * @param name  用户姓名
     * @return  Result<PageResultBean<List<User>>>
     */
    @GetMapping("/pageList")
    @Operation(summary = "用户列表分页查询")
    @Parameters({
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "token"),
            @Parameter(name = "pageSize", required = true, description = "每页显示的条数"),
            @Parameter(name = "currentPage", required = true, description = "要查询的页"),
            @Parameter(name = "name", description = "用户姓名", required = false)
    })
    public Result<PageResultBean<User>> pageList(@RequestParam int pageSize, @RequestParam int currentPage, @Nullable @RequestParam String name) {
        IPage<User> page = userService.pageList(pageSize, currentPage, name);
        if (page == null) {
            return Result.error("查询失败");
        }
        //PageResultBean<User> pageResultBean = new PageResultBean<User>(page.getTotal(), page.getRecords());
        return Result.success(PageResultBean.getInstance(page.getTotal(), page.getRecords()));

    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    @Parameters({
            @Parameter(name = "loginName", description = "登录名", required = true, schema = @Schema(type = "sting")),
            @Parameter(name = "password", description = "密码", required = true, schema = @Schema(type = "sting"))
    })
    public Result<String> login(@RequestBody User user) {
        if (user == null || "".equals(user.getLoginName()) || user.getLoginName() == null || "".equals(user.getPassword()) || user.getPassword() == null) {
            return Result.error("用户名密码不能为空");
        }
        // 检验用户名是否存在
        User eruser = userService.findByLoginName(user.getLoginName());
        if (eruser == null) {
            return Result.error("用户名不存在");
        }
        // 检验用户密码是否正确
        if (Md5Util.getMD5String(user.getPassword()).equals(eruser.getPassword())) {
//        if (user.getPassword().equals(eruser.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", eruser.getId());
            claims.put("name", eruser.getName());
            claims.put("loginName", eruser.getLoginName());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }



    /**
     * 获取当前登录用户信息
     * @return  User
     */
    @GetMapping("/currentUser")
    @Operation(summary = "获取当前登录用户信息")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "token")
    public Result<User> getCurrentUser() {
        Map<String, Object> userSession = ThreadLocalUtil.get();
        int id = (int) userSession.get("id");
        User user = userService.getUserById(id);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户状态异常");
    }

    /**
     * 用户注册
     * @param user 用户数据集
     * @return stat
     * */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<Integer> register(@RequestBody @Valid User user) {
        Integer stat = userService.insertOneUser(user);
        return Result.success(stat);
    }
}
