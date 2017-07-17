package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Category;
import com.hezhujun.shopping.model.Product;
import com.hezhujun.shopping.model.Regular;
import com.hezhujun.shopping.model.Repertory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hezhujun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
@Transactional
public class ProductMapperTest {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RepertoryMapper repertoryMapper;
    @Before
    public void setUp() throws Exception {
        Category category = new Category(1, "女装");
        Regular regular = new Regular(100, new BigDecimal("1"));
        for (int i = 0; i < 100; i++) {
            Repertory repertory = new Repertory(100);
            assertEquals(1, repertoryMapper.insert(repertory));
            assertNotNull(repertory.getId());
            Product product = new Product();
            product.setName("产品" + i);
            product.setCategory(category);
            product.setDescription("描述" + i);
            product.setImgUrl("url" + i);
            product.setPrice(new BigDecimal("60.99"));
            product.setRegular(regular);
            product.setRepertory(repertory);
            assertEquals(1, productMapper.insertSelective(product));
            System.out.println(product);
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void insertSelective() throws Exception {
        Category category = new Category(1, "女装");
        Regular regular = new Regular(100, new BigDecimal("1"));
        Repertory repertory = new Repertory(100);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        Product product = new Product();
        product.setName("产品");
        product.setCategory(category);
        product.setDescription("描述");
        product.setImgUrl("url");
        product.setPrice(new BigDecimal("60.99"));
        product.setRegular(regular);
        product.setRepertory(repertory);
        assertEquals(1, productMapper.insertSelective(product));
        System.out.println(product);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void count() throws Exception {
        Integer count = productMapper.count(1);
        assertNotNull(count);
        System.out.println(count);
    }

    @Test
    public void selectByCategory() throws Exception {
        List<Product> productList = productMapper.selectByCategory(1, 60, 10);
        assertNotNull(productList);
        for (Product p :
                productList) {
            System.out.println(p);
        }
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
        Category category = new Category(1, "女装");
        Regular regular = new Regular(100, new BigDecimal("1"));
        Repertory repertory = new Repertory(100);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        Product product = new Product();
        product.setName("产品");
        product.setCategory(category);
        product.setDescription("描述");
        product.setImgUrl("url");
        product.setPrice(new BigDecimal("60.99"));
        product.setRegular(regular);
        product.setRepertory(repertory);
        assertEquals(1, productMapper.insertSelective(product));
        System.out.println(product);
        product.setPrice(new BigDecimal("100.50"));
        System.out.println(product);
        productMapper.updateByPrimaryKeySelective(product);
        product = productMapper.selectByPrimaryKey(product.getId());
        assertEquals(new BigDecimal("100.50"), product.getPrice());
        System.out.println(product);
    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}