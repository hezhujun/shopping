package com.hezhujun.shopping.controller;

import com.hezhujun.shopping.model.Category;
import com.hezhujun.shopping.model.Result;
import com.hezhujun.shopping.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hezhujun on 2017/7/10.
 * 处理类别请求
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ProductService productService;

    /**
     * 查询类别
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> list() {
        if (logger.isDebugEnabled()) {
            logger.debug("list category");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            List<Category> categoryList = productService.listCategory();
            result.put("categories", categoryList);
        }
        catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 添加类别
     * @param category
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> save(@RequestParam String category) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save category: %s", category));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            Category c = productService.saveCategory(category);
            result.put("category", c);
        }
        catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            if (e instanceof DuplicateKeyException) {
                res.setErr("类别名重复");
            }
            else {
                res.setErr(e.getMessage());
            }
        }
        result.put("result", res);
        return result;
    }
}
