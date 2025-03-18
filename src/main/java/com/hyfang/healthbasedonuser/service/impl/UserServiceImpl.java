package com.hyfang.healthbasedonuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hyfang.healthbasedonuser.entity.User;
import com.hyfang.healthbasedonuser.mapper.UserMapper;
import com.hyfang.healthbasedonuser.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyfang.healthbasedonuser.utils.Md5Util;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyfang
 * @since 2025-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public IPage<User> pageList(int pageSize, int currentPage, String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(name != null && !name.isEmpty(), User::getName, name);

        IPage<User> page = new Page<>(currentPage, pageSize);
        userMapper.selectPage(page, wrapper);
        return page;
    }

    @Override
    public User findByLoginName(String loginName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getLoginName, loginName);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer insertOneUser(User user) {
        // 检查用户名是否已存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getLoginName, user.getLoginName());
        if (userMapper.exists(queryWrapper)) {
            throw new RuntimeException("用户名已存在");
        }

        // 使用MD5加密密码
        String rawPassword = user.getPassword();
        user.setPassword(Md5Util.getMD5String(rawPassword));

        // 验证性别参数
        if (!"0".equals(user.getGender()) && !"1".equals(user.getGender())) {
            throw new IllegalArgumentException("性别参数不合法");
        }

        // 保存用户
        return userMapper.insert(user);
    }
}
