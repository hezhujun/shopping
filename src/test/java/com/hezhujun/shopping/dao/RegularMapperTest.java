package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Regular;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by hezhujun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class RegularMapperTest {

    @Autowired
    RegularMapper regularMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void deleteByPrimaryKey() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        // 初始化数据并测试
        // 之后不需要执行
        for (int i = 1; i <= 100; i++) {
            Regular regular = new Regular(new BigDecimal(toDiscount(i)));
            assertEquals(1, regularMapper.insert(regular));
        }
    }

    // 把discount改写成字符
    // 如 20 --> 0.20
    private String toDiscount(int discount) {
        char[] discountChar = new char[4];
        String res = String.valueOf(discount);
        int i = 3;
        for (int j = res.length() - 1; j >= 0 && i >= 0; i--, j--) {
            discountChar[i] = res.charAt(j);
        }
        for (; i >= 0; i--) {
            discountChar[i] = '0';
        }
        discountChar[0] = discountChar[1];
        discountChar[1] = '.';
        return new String(discountChar);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        Regular regular = regularMapper.selectByPrimaryKey(10);
        assertNotNull(regular);
        System.out.println(regular);
    }

    @Test
    public void selectByDiscount() throws Exception {
        // 直接0.20, 不精确
//        Regular regular = regularMapper.selectByDiscount(new BigDecimal(0.20));
        Regular regular = regularMapper.selectByDiscount(new BigDecimal("0.20"));
        assertNotNull(regular);
        System.out.println(regular);
        regular = regularMapper.selectByDiscount(new BigDecimal("2.0"));
        assertNull(regular);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}