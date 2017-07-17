package com.hezhujun.shopping.service;

import com.hezhujun.shopping.model.Category;
import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Product;

import java.util.List;

/**
 * Created by hezhujun on 2017/7/10.
 * 负责处理与商品有关的操作
 */
public interface ProductService {

    /**
     * 添加产品类别
     *
     * @param category 类别名
     * @return 类别对象
     * @throws Exception
     */
    Category saveCategory(String category) throws Exception;

    /**
     * 获取所有产品类别
     *
     * @return 类别对象列表
     * @throws Exception
     */
    List<Category> listCategory() throws Exception;

    /**
     * 添加商品
     *
     * @param product 商品类别
     * @return 商品对象
     * @throws Exception
     */
    Product saveProduct(Product product) throws Exception;

    /**
     * 获取商品列表，根据传入的PageBean查询，把查询结果放入PageBean中
     *
     * @param categoryId 类别编号
     * @param products   商品分页对象
     * @throws Exception
     */
    void listProduct(Integer categoryId, PageBean<Product> products) throws Exception;

    /**
     * 更新商品信息
     *
     * @param product 商品信息
     * @return 商品对象
     * @throws Exception
     */
    Product updateProduct(Product product) throws Exception;


}
