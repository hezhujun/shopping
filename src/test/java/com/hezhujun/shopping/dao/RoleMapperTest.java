package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Role;
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
public class RoleMapperTest {

    @Autowired
    RoleMapper roleMapper;

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
        Role role = new Role();
        role.setRoleName("测试人员");
        assertEquals(1, roleMapper.insert(role));
        assertNotNull(role.getId());
        System.out.println(role);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        assertNotNull(roleMapper);
        Role role = roleMapper.selectByPrimaryKey(1);
        assertNotNull(role);
        System.out.println(role);
    }

    @Test
    public void selectAll() throws Exception {
        List<Role> roles = roleMapper.selectAll();
        assertNotNull(roles);
        assertNotEquals(0, roles.size());
        for (Role r :
                roles) {
            System.out.println(r);
        }
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Test
    public void updateByPrimaryKey() throws Exception {

    }

}