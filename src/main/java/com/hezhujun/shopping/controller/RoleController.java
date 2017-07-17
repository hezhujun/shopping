package com.hezhujun.shopping.controller;

import com.hezhujun.shopping.model.Category;
import com.hezhujun.shopping.model.Result;
import com.hezhujun.shopping.model.Role;
import com.hezhujun.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hezhujun on 2017/7/10.
 * 处理角色有关的请求
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private UserService userService;

    /**
     * 获取角色列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> list() {
        if (logger.isDebugEnabled()) {
            logger.debug("list role");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            List<Role> roleList = userService.listRole();
            result.put("roles", roleList);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }
}
