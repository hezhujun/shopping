package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Repertory;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface RepertoryMapper {

    /**
     * 通过id删除对象
     *
     * @param id 库存id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 库存对象
     * @return 影响行数
     */
    int insert(Repertory record);

    /**
     * 插入不为null的信息
     *
     * @param record 库存对象
     * @return 影响行数
     */
    int insertSelective(Repertory record);

    /**
     * 通过id查询库存信息
     *
     * @param id 库存id
     * @return 库存对象
     */
    Repertory selectByPrimaryKey(Integer id);

    /**
     * 更新不为null的信息
     *
     * @param record 库存信息
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Repertory record);

    /**
     * 更新信息
     *
     * @param record 库存对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Repertory record);
}