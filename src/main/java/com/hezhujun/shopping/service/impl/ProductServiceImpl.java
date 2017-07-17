package com.hezhujun.shopping.service.impl;

import com.hezhujun.shopping.dao.CategoryMapper;
import com.hezhujun.shopping.dao.ProductMapper;
import com.hezhujun.shopping.dao.RegularMapper;
import com.hezhujun.shopping.dao.RepertoryMapper;
import com.hezhujun.shopping.model.*;
import com.hezhujun.shopping.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hezhujun on 2017/7/10.
 * ProductService的实现类
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RegularMapper regularMapper;
    @Autowired
    private RepertoryMapper repertoryMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 保存类别
     * @param category 类别名
     * @return
     * @throws Exception
     */
    @Override
    public Category saveCategory(String category) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save category: %s", category));
        }
        Category c = new Category(category);
        categoryMapper.insert(c);
        return c;
    }

    /**
     * 查询类别
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> listCategory() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("list category");
        }
        return categoryMapper.selectAll();
    }

    /**
     * 保存商品
     * @param product 商品类别
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public Product saveProduct(Product product) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save product: %s", product.toString()));
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new Exception("商品的类别ID为null");
        }
        if (product.getRegular() == null || product.getRegular().getDiscount() == null) {
            throw new Exception("折扣没有设置");
        }
        Regular regular = product.getRegular();
        int res = regularMapper.insert(regular);
        if (!(res == 1) || regular.getId() == null){
            throw new Exception("保存折扣信息失败");
        }
        product.setRegular(regular);
        if (product.getRepertory() == null || product.getRepertory().getCount() == null) {
            throw new Exception("库存没有设置");
        }
        res = repertoryMapper.insert(product.getRepertory());
        if (!(res == 1)) {
            throw new Exception("保存库存信息失败");
        }
        res = productMapper.insertSelective(product);
        if (!(res == 1)) {
            throw new Exception("保存商品信息失败");
        }
        return product;
    }

    /**
     * 查询商品
     * @param categoryId 类别编号
     * @param products   商品分页对象
     * @throws Exception
     */
    @Override
    public void listProduct(Integer categoryId, PageBean<Product> products) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("list product: at page %d, rows: %d",
                    products.getPage(), products.getRows()));
        }
        int totalRows = productMapper.count(categoryId);
        products.setTotalRows(totalRows);
        int offset = (products.getPage() - 1) * products.getRows();
        products.setBeans(productMapper.selectByCategory(categoryId, offset, products.getRows()));
    }

    /**
     * 更新商品
     * @param product 商品信息
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public Product updateProduct(Product product) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("update product: %s", product.toString()));
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new Exception("商品的类别ID为null");
        }
        if (product.getRegular() == null || product.getRegular().getId() == null) {
            throw new Exception("折扣ID为null");
        }
        if (product.getRepertory() == null || product.getRepertory().getId() == null) {
            throw new Exception("库存ID为null");
        }
        regularMapper.updateByPrimaryKey(product.getRegular());
        repertoryMapper.updateByPrimaryKey(product.getRepertory());
        productMapper.updateByPrimaryKeySelective(product);
        return productMapper.selectByPrimaryKey(product.getId());
    }
}
