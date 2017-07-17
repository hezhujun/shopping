package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.User;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface UserMapper {
    /**
     * 通过id删除信息
     *
     * @param id 用户id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 用户对象
     * @return 影响行数
     */
    int insert(User record);

    /**
     * 插入不为空的数据
     *
     * @param record 用户对象
     * @return 影响行数
     */
    int insertSelective(User record);

    /**
     * 统计用户个数
     *
     * @return 统计结果
     */
    int count();

    /**
     * 通过id查询用户信息
     *
     * @param id 用户id
     * @return 用户对象
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 通过用户名和密码查询用户
     *
     * @param user 用户对象，包涵用户名和密码
     * @return 用户对象
     */
    User selectByUsernameAndPassword(User user);

    /**
     * 分页查询用户信息
     *
     * @param offset 偏移量
     * @param rows   行数
     * @return 用户列表
     */
    List<User> selectAll(@Param("offset") Integer offset, @Param("rows") Integer rows);

    /**
     * 更新不为null的数据
     *
     * @param record 用户对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新用户信息
     *
     * @param record 用户对象
     * @return 影响行数
     */
    int updateByPrimaryKey(User record);
}