package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Role;
import com.hezhujun.shopping.model.User;
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
@Transactional
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < 50; i++) {
            User user = new User();
            user.setUsername("test" + i);
            user.setPassword("password" + i);
            Role role = new Role(1, "普通用户");
            user.setRole(role);
            assertEquals(1, userMapper.insert(user));
            assertNotNull(user.getId());
        }
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
        User user = new User();
        user.setUsername("test");
        user.setPassword("password1");
        Role role = new Role(1, "普通用户");
        user.setRole(role);
        assertEquals(1, userMapper.insert(user));
        assertNotNull(user.getId());
        System.out.println(user);
    }

    @Transactional
    @Test
    public void insertSelective() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password1");
        user.setName("test");
        Role role = new Role(1, "普通用户");
        user.setRole(role);
        assertEquals(1, userMapper.insert(user));
        assertNotNull(user.getId());
        System.out.println(user);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        User user = userMapper.selectByPrimaryKey(1);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void selectAll() throws Exception {
        System.out.println(userMapper.count());
        List<User> users = userMapper.selectAll(0, 10);
        System.out.println(users.size());
        assertNotNull(users);
        for (User u :
                users) {
            System.out.println(u);
        }
    }

    @Test
    public void count() throws Exception {
        System.out.println(userMapper.count());
    }

    @Test
    public void selectByUsernameAndPassword() throws Exception {
        User user = new User();
        user.setUsername("root");
        user.setPassword("4QrcOUm6Wau+VuBX8g+IPg==");
        user = userMapper.selectByUsernameAndPassword(user);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}