package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Repertory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by hezhujun on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class RepertoryMapperTest {
    @Autowired
    RepertoryMapper repertoryMapper;
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
        Repertory repertory = new Repertory(50);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        System.out.println(repertory);
    }

    @Test
    public void insertSelective() throws Exception {

    }

    @Transactional
    @Test
    public void selectByPrimaryKey() throws Exception {
        Repertory repertory = new Repertory(50);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        repertory = repertoryMapper.selectByPrimaryKey(repertory.getId());
        assertNotNull(repertory);
        assertEquals(new Integer(50), repertory.getCount());
        System.out.println(repertory);
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {

    }

    @Transactional
    @Test
    public void updateByPrimaryKey() throws Exception {
        Repertory repertory = new Repertory(50);
        assertEquals(1, repertoryMapper.insert(repertory));
        assertNotNull(repertory.getId());
        repertory.setCount(repertory.getCount() - 1);
        assertEquals(1, repertoryMapper.updateByPrimaryKey(repertory));
        repertory = repertoryMapper.selectByPrimaryKey(repertory.getId());
        assertEquals(new Integer(49), repertory.getCount());
        System.out.println(repertory);
    }

}