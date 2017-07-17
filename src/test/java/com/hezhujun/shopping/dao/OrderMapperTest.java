package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hezhujun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
@Transactional
public class OrderMapperTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RepertoryMapper repertoryMapper;
    @Autowired
    OrderMapper orderMapper;

    private User user;
    private Product product;
    private Order order;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setUsername("test");
        user.setPassword("password1");
        Role role = new Role(1, "普通用户");
        user.setRole(role);
        assertEquals(1, userMapper.insert(user));
        assertNotNull(user.getId());

        Category category = new Category(1, "女装");
        Regular regular = new Regular(100, new BigDecimal("1"));
        Repertory repertory = new Repertory(100);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        product = new Product();
        product.setName("产品");
        product.setCategory(category);
        product.setDescription("描述");
        product.setImgUrl("url");
        product.setPrice(new BigDecimal("60.99"));
        product.setRegular(regular);
        product.setRepertory(repertory);
        assertEquals(1, productMapper.insertSelective(product));

        order = new Order();
        order.setPrice(product.getPrice());
        order.setAddressee("收件人");
        order.setPhone("12345678911");
        order.setAddress("收货地址");
        order.setUser(user);
        order.setProduct(product);
        order.setTime(sdf.parse("2017-6-30"));
        assertEquals(1, orderMapper.insert(order));
        assertNotNull(order.getId());
        System.out.println(order);

        int i = 0;

        for (; i < 10; i++) {
            order = new Order();
            order.setPrice(product.getPrice());
            order.setAddressee("收件人" + i);
            order.setPhone("12345678911");
            order.setAddress("收货地址" + i);
            order.setUser(user);
            order.setProduct(product);
            order.setState(Order.ORDER_STATE_NEW);
            order.setTime(sdf.parse("2017-7-" + (i + 1)));
            assertEquals(1, orderMapper.insert(order));
            assertNotNull(order.getId());
        }
        for (; i < 20; i++) {
            order = new Order();
            order.setPrice(product.getPrice());
            order.setAddressee("收件人" + i);
            order.setPhone("12345678911");
            order.setAddress("收货地址" + i);
            order.setUser(user);
            order.setProduct(product);
            order.setState(Order.ORDER_STATE_CONFIRM);
            order.setSuccess(Order.ORDER_SUCCESS);
            order.setTime(sdf.parse("2017-7-" + (i + 1)));
            assertEquals(1, orderMapper.insert(order));
            assertNotNull(order.getId());
        }

        i = 0;
        for (; i < 10; i++) {
            order = new Order();
            order.setPrice(product.getPrice());
            order.setAddressee("收件人" + i);
            order.setPhone("12345678911");
            order.setAddress("收货地址" + i);
            order.setUser(user);
            order.setProduct(product);
            order.setState(Order.ORDER_STATE_REJECT);
            order.setTime(sdf.parse("2017-7-" + (i + 1)));
            assertEquals(1, orderMapper.insert(order));
            assertNotNull(order.getId());
        }
        for (; i < 20; i++) {
            order = new Order();
            order.setPrice(product.getPrice());
            order.setAddressee("收件人" + i);
            order.setPhone("12345678911");
            order.setAddress("收货地址" + i);
            order.setUser(user);
            order.setProduct(product);
            order.setState(Order.ORDER_STATE_CLOSE);
            order.setTime(sdf.parse("2017-7-" + (i + 1)));
            assertEquals(1, orderMapper.insert(order));
            assertNotNull(order.getId());
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
        System.out.println(user);
        System.out.println(product);
        Order order = new Order();
        order.setPrice(product.getPrice());
        order.setAddressee("收件人");
        order.setPhone("12345678911");
        order.setAddress("收货地址");
        order.setUser(user);
        order.setProduct(product);
        order.setTime(new Date());
        assertEquals(1, orderMapper.insert(order));
        assertNotNull(order.getId());
        System.out.println(order);
    }

    @Test
    public void insertSelective() throws Exception {
        System.out.println(user);
        System.out.println(product);
        Order order = new Order();
        order.setPrice(product.getPrice());
        order.setAddressee("收件人");
        order.setPhone("12345678911");
        order.setAddress("收货地址");
        order.setUser(user);
        order.setProduct(product);
        order.setTime(new Date());
        assertEquals(1, orderMapper.insertSelective(order));
        assertNotNull(order.getId());
        System.out.println(order);
    }

    @Test
    public void count() throws Exception {
        assertEquals(11, orderMapper.count(Order.ORDER_STATE_NEW, null, null, null, null));
        assertEquals(10, orderMapper.count(Order.ORDER_STATE_CONFIRM, null, null, null, null));
        assertEquals(10, orderMapper.count(Order.ORDER_STATE_REJECT, null, null, null, null));
        assertEquals(10, orderMapper.count(Order.ORDER_STATE_CLOSE, null, null, null, null));

        assertEquals(41, orderMapper.count(null, null, null, user.getId(), null));
        assertEquals(10, orderMapper.count(Order.ORDER_STATE_CLOSE, null, null, user.getId(), null));

        Date from = sdf.parse("2017-7-1");
        Date to = sdf.parse("2017-7-10");
        assertEquals(20, orderMapper.count(null, from, to, user.getId(), null));

        from = sdf.parse("2017-7-11");
        to = sdf.parse("2017-7-20");
        assertEquals(20, orderMapper.count(null, from, to, user.getId(), null));
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        order = orderMapper.selectByPrimaryKey(order.getId());
        assertNotNull(order);
        assertNotNull(order.getUser());
        assertNotNull(order.getProduct());
        System.out.println(order);
    }

    @Test
    public void select() throws Exception {
        Date from = sdf.parse("2017-7-1");
        Date to = sdf.parse("2017-7-10");
        List<Order> orderList = orderMapper.select(null, from, to, user.getId(), null, 0, 100);
        assertEquals(20, orderList.size());
        for (Order o :
                orderList) {
            System.out.println(o);
        }
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

    @Test
    public void showAll() {
        List<Order> orderList = orderMapper.select(null, null, null, null, null, 0, 1000);
        for (Order o :
                orderList) {
            System.out.println(o);
        }
    }

}