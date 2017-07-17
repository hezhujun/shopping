package com.hezhujun.shopping.controller;

import com.hezhujun.shopping.model.Category;
import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Product;
import com.hezhujun.shopping.model.Result;
import com.hezhujun.shopping.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by hezhujun on 2017/7/10.
 * 处理商品有关的请求
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    /**
     * 获取商品列表
     * @param categoryId
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> list(@RequestParam Integer categoryId,
                                    @RequestParam(name = "page", defaultValue = "1") int page) {
        if (logger.isDebugEnabled()) {
            logger.debug("list product");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            PageBean<Product> productPageBean = new PageBean<>(page, 10);
            productService.listProduct(categoryId, productPageBean);
            result.put("products", productPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }


    /**
     * 保存商品
     * @param product
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> save(@RequestBody Product product) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save product: %s", product));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            Product p = productService.saveProduct(product);
            result.put("product", p);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 更新商品
     * @param product
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Product product) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("update product: %s", product));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            Product p = productService.updateProduct(product);
            result.put("product", p);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

}
