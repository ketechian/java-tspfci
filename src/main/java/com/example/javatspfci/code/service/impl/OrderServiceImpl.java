package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.dto.order.OrderInfoDto;
import com.example.javatspfci.code.entity.po.Order;
import com.example.javatspfci.code.entity.vo.OrderQueryMsg;
import com.example.javatspfci.code.mapper.OrderMapper;
import com.example.javatspfci.code.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZCL
 * @since 2022-05-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 添加订单
     * @param orderId 订单号
     * @param creatorId 店家编号
     * @param receiverId 厂家信息
     * @param info 订单信息
     * @param remark 备注信息
     * @param type 订单类型
     * @param price 订单价格
     * @param status 订单状态
     * @return
     */
    @Override
    public Boolean createOrder(String orderId, String creatorId, String receiverId, String info, String remark, String type, BigDecimal price, Integer status) {
        return orderMapper.createOrder(orderId, creatorId, receiverId, info, remark, type, price, status) == 1;
    }

    /**
     * 设置订单配送员
     * @param orderId 订单ID
     * @param deliveryId 配送员ID
     * @return
     */
    @Override
    public Boolean setOrderDelivery(String orderId, String deliveryId) {
        return orderMapper.setOrderDelivery(orderId, deliveryId) == 1;
    }

    /**
     * 更订单状态
     * @param orderId 订单ID
     * @param statusCode 订单状态
     * @return
     */
    @Override
    public Boolean updateOrderStatus(String orderId, Integer statusCode) {
        return orderMapper.updateOrderStatus(orderId, statusCode) == 1;
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param reason 原因
     * @return
     */
    @Override
    public Boolean orderCancel(String orderId, String reason) {
        return orderMapper.orderCancel(orderId, reason) == 1;
    }

    /**
     * 根据店家ID和厂家ID取消订单
     * @param factoryId 厂家Id
     * @param storeId 店家Id
     * @return
     */
    @Override
    public Boolean orderCancelByFactoryAndStore(String factoryId, String storeId) {
        return orderMapper.orderCancelByFactoryAndStore(factoryId, storeId) >= 1;
    }

    /**
     * 根据配送员Id取消订单
     * @param deliveryId 配送员Id
     * @return
     */
    @Override
    public Boolean orderCancelByDelivery(String deliveryId) {
        return orderMapper.orderCancelByDelivery(deliveryId) >= 1;
    }

    /**
     * 店家查询订单
     * @param storeId 店家ID
     * @return
     */
    @Override
    public List<OrderQueryMsg> listOrderByStore(String storeId) {
        return orderMapper.listOrderByStore(storeId);
    }

    /**
     * 厂家查询订单
     * @param factoryId 厂家ID
     * @return
     */
    @Override
    public List<OrderQueryMsg> listOrderByFactory(String factoryId) {
        return orderMapper.listOrderByFactory(factoryId);
    }

    /**
     * 配送员查询订单
     * @param deliveryId 配送员ID
     * @return
     */
    @Override
    public List<OrderQueryMsg> listOrderByDelivery(String deliveryId) {
        return orderMapper.listOrderByDelivery(deliveryId);
    }

    /**
     * 查询单个订单
     * @param orderId 订单ID
     * @return
     */
    @Override
    public OrderQueryMsg queryOneOrder(String orderId) {
        return orderMapper.queryOneOrder(orderId);
    }

    /**
     * 查询月交易额
     * @param firstMonthDay 月初时间
     * @param lastMonthDay 月末时间
     * @return
     */
    @Override
    public BigDecimal queryMonthPay(String factoryId, LocalDateTime firstMonthDay, LocalDateTime lastMonthDay) {
        return orderMapper.queryMonthPay(factoryId, firstMonthDay, lastMonthDay);
    }

    /**
     * 查询月订单
     * @param firstMonthDay 月初时间
     * @param lastMonthDay 月末时间
     * @return
     */
    @Override
    public Integer queryMonthOrder(String factoryId, LocalDateTime firstMonthDay, LocalDateTime lastMonthDay) {
        return orderMapper.queryMonthOrder(factoryId, firstMonthDay, lastMonthDay);
    }
}
