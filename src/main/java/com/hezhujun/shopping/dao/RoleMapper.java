package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Role;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 操作role表的类
 */
@MapperScan
public interface RoleMapper {

    /**
     * 通过id删除数据
     *
     * @param id 角色id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 角色信息
     * @return 影响行数
     */
    int insert(Role record);

    /**
     * 出入不为null的数据
     *
     * @param record 角色对象
     * @return 影响行数
     */
    int insertSelective(Role record);

    /**
     * 通过id查询角色信息
     *
     * @param id 角色id
     * @return 角色对象
     */
    Role selectByPrimaryKey(Integer id);

    /**
     * 查询所有角色信息
     *
     * @return 角色列表
     */
    List<Role> selectAll();

    /**
     * 更新不为null的数据
     *
     * @param record 角色对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新信息
     *
     * @param record 角色对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Role record);
}