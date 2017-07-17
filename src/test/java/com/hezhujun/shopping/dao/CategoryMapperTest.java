package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hezhujun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class CategoryMapperTest {
    @Autowired
    CategoryMapper categoryMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Transactional
    @Test
    public void insert() throws Exception {
        Category category = new Category();
        category.setName("家具");
        assertEquals(1, categoryMapper.insert(category));
        System.out.println(category);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {

    }

    @Test
    public void selectAll() throws Exception {
        List<Category> categories = categoryMapper.selectAll();
        assertNotNull(categories);
        assertNotEquals(0, categories.size());
        for (Category c :
                categories) {
            System.out.println(c);
        }
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}