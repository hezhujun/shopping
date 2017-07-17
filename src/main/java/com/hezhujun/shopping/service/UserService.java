package com.hezhujun.shopping.service;

import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Role;
import com.hezhujun.shopping.model.User;

import java.util.List;

/**
 * Created by hezhujun on 2017/7/10.
 */
public interface UserService {

    /**
     * 获取角色列表
     *
     * @return 角色列表
     * @throws Exception
     */
    List<Role> listRole() throws Exception;

    /**
     * 用户登录
     *
     * @param username 账号名
     * @param password 密码 不需要加密，函数内部加密
     * @return 用户对象
     * @throws Exception
     */
    User login(String username, String password) throws Exception;

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 用户对象
     * @throws Exception
     */
    User updateUser(User user) throws Exception;

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param oldPassword 旧密码 函数内部加密
     * @param newPassword 新密码 函数内部加密
     * @return 是否成功
     * @throws Exception
     */
    boolean updatePassword(Integer userId, String oldPassword, String newPassword) throws Exception;

    /**
     * 添加用户
     *
     * @param user 用户信息 密码内部加密
     * @return 用户对象
     * @throws Exception
     */
    User saveUser(User user) throws Exception;

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否成功
     * @throws Exception
     */
    boolean removeUser(Integer id) throws Exception;

    /**
     * 获取用户列表，根据传入的PageBean查询，把查询结果放入PageBean中
     *
     * @param users 用户分页对象
     * @throws Exception
     */
    void listUser(PageBean<User> users) throws Exception;
}
