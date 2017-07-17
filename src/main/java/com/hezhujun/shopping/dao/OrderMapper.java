package com.hezhujun.shopping.dao;

import com.hezhujun.shopping.model.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;

@MapperScan
public interface OrderMapper {
    /**
     * 通过id删除订单信息
     *
     * @param id 订单id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param record 订单对象
     * @return 影响行数
     */
    int insert(Order record);

    /**
     * 插入订单中不为null的数据
     *
     * @param record 订单对象
     * @return 影响行数
     */
    int insertSelective(Order record);

    /**
     * 获取用户所有订单数，不需要的参数设为null
     * <p>
     * 在用户浏览订单时调用
     * 产品销售商查询待处理订单时调用
     *
     * @param state   订单状态
     * @param from    范围查询的开始时间
     * @param to      范围查询的结束时间
     * @param userId  用户Id
     * @param orderId 订单Id
     * @return 总行数
     */
    int count(@Param("state") String state,
              @Param("from") Date from,
              @Param("to") Date to,
              @Param("userId") Integer userId,
              @Param("orderId") Integer orderId);

    /**
     * 通过id获取订单
     *
     * @param id 订单id
     * @return 订单对象
     */
    Order selectByPrimaryKey(Integer id);


    /**
     * 获取用户所有订单，不需要的参数设为null
     * <p>
     * 在用户浏览订单时调用
     * 产品销售商查询待处理订单时调用
     *
     * @param state   订单状态
     * @param from    范围查询的开始时间
     * @param to      范围查询的结束时间
     * @param userId  用户Id
     * @param orderId 订单Id
     * @param offset  偏移行数
     * @param rows    查询行数
     * @return 订单列表
     */
    List<Order> select(@Param("state") String state,
                       @Param("from") Date from,
                       @Param("to") Date to,
                       @Param("userId") Integer userId,
                       @Param("orderId") Integer orderId,
                       @Param("offset") int offset,
                       @Param("rows") int rows);

    /**
     * 更新订单对象中属性不为null的数据
     *
     * @param record 订单对象
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 更新订单信息
     *
     * @param record 订单对象
     * @return 影响行数
     */
    int updateByPrimaryKey(Order record);

    /**
     * 获取已经处理过订单数
     *
     * @return 订单数
     */
    int countOfDone();

    /**
     * 获取已经处理过的订单
     *
     * @param offset 偏移行数
     * @param rows   查询行数
     * @return 订单列表
     */
    List<Order> done(@Param("offset") int offset, @Param("rows") int rows);

//    /**
//     * 商家确认订单
//     * @param orderId 订单编号
//     * @return 修改行数
//     */
//    int confirm(Integer orderId);
//
//    /**
//     * 商家拒绝订单
//     * @param orderId 订单编号
//     * @return 修改行数
//     */
//    int reject(Integer orderId);
//
//    /**
//     * 用户关闭订单
//     * @param orderId 订单编号
//     * @return 修改行数
//     */
//    int close(Integer orderId);
}