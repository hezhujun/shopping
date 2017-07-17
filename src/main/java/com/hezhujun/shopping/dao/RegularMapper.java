package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Regular;
import org.mybatis.spring.annotation.MapperScan;

import java.math.BigDecimal;

@MapperScan
public interface RegularMapper {
    /**
     * 通过id删除信息
     *
     * @param id 规则信息
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 规则对象
     * @return 影响行数
     */
    int insert(Regular record);

    /**
     * 插入不为null的字段数据
     *
     * @param record 规则对象
     * @return 影响行数
     */
    int insertSelective(Regular record);

    /**
     * 通过id查询规则信息
     *
     * @param id 规则id
     * @return 规则对象
     */
    Regular selectByPrimaryKey(Integer id);

    /**
     * 通过折扣查询规则信息
     *
     * @param discount 折扣
     * @return 规则对象
     */
    Regular selectByDiscount(BigDecimal discount);

    /**
     * 更新字段不为null的信息
     *
     * @param record 规则对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Regular record);

    /**
     * 更新规则信息
     *
     * @param record 规则对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Regular record);
}