package com.hezhujun.shopping.service.impl;

import com.hezhujun.shopping.dao.OrderMapper;
import com.hezhujun.shopping.dao.ProductMapper;
import com.hezhujun.shopping.dao.RepertoryMapper;
import com.hezhujun.shopping.model.Order;
import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Product;
import com.hezhujun.shopping.model.Repertory;
import com.hezhujun.shopping.service.OrderService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hezhujun on 2017/7/10.
 * OrderService的实现类
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    /**
     * 日志器
     */
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 日期格式化类
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RepertoryMapper repertoryMapper;

    /**
     * 保存订单
     * @param order 订单信息
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public Order saveOrder(Order order) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("save order %s", order.toString()));
        }
        if (order.getUser() == null || order.getUser().getId() == null) {
            throw new Exception("订单没有对应用户");
        }
        if (order.getProduct() == null || order.getProduct().getId() == null) {
            throw new Exception("订单没有对应的商品");
        }
        order.setState(Order.ORDER_STATE_NEW);
        Product product = productMapper.selectByPrimaryKey(order.getProduct().getId());
        Repertory repertory = product.getRepertory();
        int count = repertory.getCount();
        if (count == 0) {
            throw new Exception("没有库存了");
        }
        repertory.setCount(count - 1);
        repertoryMapper.updateByPrimaryKey(repertory);
        order.setProduct(product);
        orderMapper.insertSelective(order);
        return order;
    }

    /**
     * 查询订单
     * @param orders  订单分页对象
     * @param userId  用户Id
     * @param orderId 订单编号
     * @param state   订单状态
     * @param from    查询开始时间
     * @param to      查询结束时间
     * @throws Exception
     */
    @Override
    public void listOrder(PageBean<Order> orders, Integer userId, Integer orderId, String state, Date from, Date to)
            throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("list product: at page %d, rows: %d,", orders.getPage(), orders.getRows()));
        }
        int totalRows = orderMapper.count(state, from, to, userId, orderId);
        orders.setTotalRows(totalRows);
        int offset = (orders.getPage() - 1) * orders.getRows();
        List<Order> orderList = orderMapper.select(state, from, to, userId, orderId, offset, orders.getRows());
        orders.setBeans(orderList);
    }

    /**
     * 确认订单
     * @param id     订单编号
     * @param remark 备注
     * @return
     * @throws Exception
     */
    @Override
    public boolean confirm(Integer id, String remark) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("商家确认订单号" + id);
        }
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setBusinessmanRemark(remark);
        order.setSuccess(Order.ORDER_SUCCESS);
        order.setState(Order.ORDER_STATE_CONFIRM);
        int res = orderMapper.updateByPrimaryKeySelective(order);
        return (res == 1) ? true : false;
    }

    /**
     * 拒绝订单
     * @param id     订单编号
     * @param remark 备注
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean reject(Integer id, String remark) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("商家拒绝订单号" + id);
        }
        Order order = orderMapper.selectByPrimaryKey(id);
        Repertory repertory = order.getProduct().getRepertory();
        int count = repertory.getCount();
        repertory.setCount(count + 1);
        repertoryMapper.updateByPrimaryKey(repertory);
        order.setBusinessmanRemark(remark);
        order.setSuccess(Order.ORDER_FAIL);
        order.setState(Order.ORDER_STATE_REJECT);
        int res = orderMapper.updateByPrimaryKeySelective(order);
        return (res == 1) ? true : false;
    }

    /**
     * 关闭订单
     * @param id     订单编号
     * @param remark 备注
     * @return
     * @throws Exception
     */
    @Override
    public boolean close(Integer id, String remark) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("用户关闭订单号" + id);
        }
        Order order = orderMapper.selectByPrimaryKey(id);
        order.setState(Order.ORDER_STATE_CLOSE);
        order.setUserRemark(remark);
        int res = orderMapper.updateByPrimaryKeySelective(order);
        return (res == 1) ? true : false;
    }

    /**
     * 查询已处理订单
     * @param orders 订单分页对象
     * @throws Exception
     */
    @Override
    public void listDoneOrder(PageBean<Order> orders) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("查询已处理过的订单");
        }
        int totalRows = orderMapper.countOfDone();
        orders.setTotalRows(totalRows);
        int offset = (orders.getPage() - 1) * orders.getRows();
        List<Order> orderList = orderMapper.done(offset, orders.getRows());
        orders.setBeans(orderList);
    }
}
