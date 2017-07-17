package com.hezhujun.shopping.service;

import com.hezhujun.shopping.model.Order;
import com.hezhujun.shopping.model.PageBean;

import java.util.Date;

/**
 * Created by hezhujun on 2017/7/10.
 * 负责处理与订单相关的操作
 */
public interface OrderService {

    /**
     * 保存订单信息
     *
     * @param order 订单信息
     * @return 订单对象对象
     * @throws Exception
     */
    Order saveOrder(Order order) throws Exception;

    /**
     * 获取订单列表
     * <p>
     * 查询条件可选
     *
     * @param orders  订单分页对象
     * @param orderId 订单编号
     * @param userId  用户Id
     * @param state   订单状态
     * @param from    查询开始时间
     * @param to      查询结束时间
     * @throws Exception
     */
    void listOrder(PageBean<Order> orders, Integer userId, Integer orderId, String state, Date from, Date to) throws Exception;

    /**
     * 商家确认订单
     *
     * @param id     订单编号
     * @param remark 备注
     * @return 是否成功
     * @throws Exception
     */
    boolean confirm(Integer id, String remark) throws Exception;

    /**
     * 商家拒绝订单
     *
     * @param id     订单编号
     * @param remark 备注
     * @return 是否成功
     * @throws Exception
     */
    boolean reject(Integer id, String remark) throws Exception;

    /**
     * 用户关闭订单
     *
     * @param id     订单编号
     * @param remark 备注
     * @return 是否成功
     * @throws Exception
     */
    boolean close(Integer id, String remark) throws Exception;

    /**
     * 获取已处理订单
     *
     * @param orders 订单分页对象
     * @throws Exception
     */
    void listDoneOrder(PageBean<Order> orders) throws Exception;
}
