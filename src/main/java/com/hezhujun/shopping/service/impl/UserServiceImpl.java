package com.hezhujun.shopping.service.impl;

import com.hezhujun.shopping.common.MD5Coder;
import com.hezhujun.shopping.dao.RoleMapper;
import com.hezhujun.shopping.dao.UserMapper;
import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Role;
import com.hezhujun.shopping.model.User;
import com.hezhujun.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hezhujun on 2017/7/10.
 * UserService的实现类
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有的角色
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> listRole() throws Exception {
        return roleMapper.selectAll();
    }

    /**
     * 登录
     * @param username 账号名
     * @param password 密码 不需要加密，函数内部加密
     * @return
     * @throws Exception
     */
    @Override
    public User login(String username, String password) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("user login by %s and %s", username, password));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Coder.encode(password));
        return userMapper.selectByUsernameAndPassword(user);
    }

    /**
     * 更新用户
     * @param user 用户信息
     * @return
     * @throws Exception
     */
    @Override
    public User updateUser(User user) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("update user: %s", user.toString()));
        }
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    /**
     * 更新用户密码
     * @param userId      用户ID
     * @param oldPassword 旧密码 函数内部加密
     * @param newPassword 新密码 函数内部加密
     * @return
     * @throws Exception
     */
    @Override
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) throws Exception {
        User user = userMapper.selectByPrimaryKey(userId);
        String oldPasswd = MD5Coder.encode(oldPassword);
        String newPasswd = MD5Coder.encode(newPassword);
        if (user.getPassword().equals(oldPasswd)) {
            user.setPassword(newPasswd);
            userMapper.updateByPrimaryKeySelective(user);
            return true;
        } else {
            throw new Exception("旧密码不匹配");
        }
    }

    /**
     * 添加用户
     * @param user 用户信息 密码内部加密
     * @return
     * @throws Exception
     */
    @Override
    public User saveUser(User user) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save user: %s", user.toString()));
        }
        user.setPassword(MD5Coder.encode(user.getPassword()));
        userMapper.insertSelective(user);
        user.setRole(roleMapper.selectByPrimaryKey(user.getRole().getId()));
        return user;
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return
     * @throws Exception
     */
    @Override
    public boolean removeUser(Integer id) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("remove user: %d", id));
        }
        int res = userMapper.deleteByPrimaryKey(id);
        return (res == 1) ? true : false;
    }

    /**
     * 获取用户
     * @param users 用户分页对象
     * @throws Exception
     */
    @Override
    public void listUser(PageBean<User> users) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("list user at page %d, rows: %d", users.getPage(), users.getRows()));
        }
        users.setTotalRows(userMapper.count());
        int offset = (users.getPage() - 1) * users.getRows();
        users.setBeans(userMapper.selectAll(offset, users.getRows()));
    }
}
