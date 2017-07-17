package com.hezhujun.shopping.controller;

import com.hezhujun.shopping.model.Order;
import com.hezhujun.shopping.model.PageBean;
import com.hezhujun.shopping.model.Result;
import com.hezhujun.shopping.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by hezhujun on 2017/7/10.
 * 处理订单有关的请求
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    /**
     * 提交订单
     * @param order
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map<String, Object> submit(@RequestBody Order order) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("submit order %s", order.toString()));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            Order o = orderService.saveOrder(order);
            result.put("order", o);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 查询订单
     * @param userId
     * @param orderId
     * @param fromLong
     * @param toLong
     * @param state
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Map<String, Object> list(@RequestParam(name = "userId", defaultValue = "0") Integer userId,
                                    @RequestParam(name = "orderId", defaultValue = "0") Integer orderId,
                                    @RequestParam(name = "from", defaultValue = "0") long fromLong,
                                    @RequestParam(name = "to", defaultValue = "0") long toLong,
                                    @RequestParam(name = "state", defaultValue = "") String state,
                                    @RequestParam(name = "page", defaultValue = "1") int page) {
        if (logger.isDebugEnabled()) {
            logger.debug("查询订单");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            Date from, to;
            PageBean<Order> orderPageBean = new PageBean<>(page, 10);
            if (userId.equals(0)) {
                userId = null;
            }
            if (orderId.equals(0)) {
                orderId = null;
            }
            if (fromLong == 0) {
                from = null;
            }
            else {
                from = new Date(fromLong);
            }
            if (toLong == 0) {
                to = null;
            }
            else {
                to = new Date(toLong);
            }
            if ("".equals(state)) {
                state = null;
            }
            orderService.listOrder(orderPageBean, userId, orderId, state, from, to);
            result.put("orders", orderPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 查询待处理订单
     * @param page
     * @return
     */
    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public Map<String, Object> todo(@RequestParam(name = "page", defaultValue = "1") int page) {
        if (logger.isDebugEnabled()) {
            logger.debug("list undeal orders");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            PageBean<Order> orderPageBean = new PageBean<>(page, 10);
            orderService.listOrder(orderPageBean,
                    null,
                    null,
                    Order.ORDER_STATE_NEW,
                    null,
                    null);
            result.put("orders", orderPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 查询已处理订单
     * @param page
     * @return
     */
    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public Map<String, Object> done(@RequestParam(name = "page", defaultValue = "1") int page) {
        if (logger.isDebugEnabled()) {
            logger.debug("list deal orders");
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            PageBean<Order> orderPageBean = new PageBean<>(page, 10);
            orderService.listDoneOrder(orderPageBean);
            result.put("orders", orderPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 确认订单
     * @param orderId
     * @param remark
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public Map<String, Object> confirm(Integer orderId, String remark) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("confirm order %d %s", orderId, remark));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            boolean r = orderService.confirm(orderId, remark);
            res.setSuccess(r);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 拒绝订单
     * @param orderId
     * @param remark
     * @return
     */
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    public Map<String, Object> reject(Integer orderId, String remark) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("reject order %d %s", orderId, remark));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            boolean r = orderService.reject(orderId, remark);
            res.setSuccess(r);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }

    /**
     * 关闭订单
     * @param orderId
     * @param remark
     * @return
     */
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public Map<String, Object> close(Integer orderId, String remark) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("order close %d %s", orderId, remark));
        }
        Map<String, Object> result = new HashMap<>();
        Result res = new Result();
        try {
            boolean r = orderService.close(orderId, remark);
            res.setSuccess(r);
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setErr(e.getMessage());
        }
        result.put("result", res);
        return result;
    }
}
