package com.hyfang.healthbasedonuser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hyfang.healthbasedonuser.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyfang
 * @since 2025-03-16
 */
public interface UserService extends IService<User> {

    //分页条件查询
    IPage<User> pageList(int pageSize, int currentPage, String name);

    //通过用户名查询查询
    User findByLoginName(String loginName);

    //查询当前用户id
    User getUserById(int id);

    //插入一条用户记录
    Integer insertOneUser(User user);
}
