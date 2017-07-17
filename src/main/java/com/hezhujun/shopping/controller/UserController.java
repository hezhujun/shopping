package com.hezhujun.shopping.controller;

import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Result;
import com.hezhujun.shopping.model.Role;
import com.hezhujun.shopping.model.User;
import com.hezhujun.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hezhujun on 2017/7/10.
 * 处理用户有关的请求
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 处理登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestParam(name = "username") String username,
                                     @RequestParam(name = "password") String password) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("user login by %s and %s", username, password));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            User user = userService.login(username, password);
            if (user == null) {
                res.setSuccess(false);
                res.setErr("账号或密码错误");
            } else {
                result.put("user", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 处理更新请求
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody User user) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("user update%s", user.toString()));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            user = userService.updateUser(user);
            result.put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 更新用户密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public Map<String, Object> updatePassword(@RequestParam Integer userId,
                                              @RequestParam String oldPassword,
                                              @RequestParam String newPassword) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("user %d update password %s %s", userId, oldPassword, newPassword));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            userService.updatePassword(userId, oldPassword, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> save(@RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam Integer roleId) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save user %s %s %d", username, password, roleId));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(new Role(roleId, null));
            user = userService.saveUser(user);
            result.put("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            if (e instanceof DuplicateKeyException) {
                res.setErr("用户名重复");
            }
            else {
                res.setErr(e.getMessage());
            }
        }
        result.put("result", res);
        return result;
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Map<String, Object> remove(@RequestParam Integer userId) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("remove user %d", userId));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            res.setSuccess(userService.removeUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> list(@RequestParam(name = "page", defaultValue = "1") int page) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("查询用户列表"));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            PageBean<User> userPageBean = new PageBean<>(page, 10);
            userService.listUser(userPageBean);
            result.put("users", userPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }
}
