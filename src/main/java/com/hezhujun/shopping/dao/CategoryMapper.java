package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Category;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface CategoryMapper {
    /**
     * 通过id删除类别
     *
     * @param id 类别id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 类别信息
     * @return 影响行数
     */
    int insert(Category record);

    /**
     * 插入类别对象中属性不为null的字段
     *
     * @param record 类别对象
     * @return 影响行数
     */
    int insertSelective(Category record);

    /**
     * 通过id获取类别
     *
     * @param id 类别id
     * @return 类别对象
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 获取所有类别
     *
     * @return 类别列表
     */
    List<Category> selectAll();

    /**
     * 更新类别对象中属性不为null的信息
     *
     * @param record 类别对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 更新类别信息
     *
     * @param record 类别对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Category record);
}