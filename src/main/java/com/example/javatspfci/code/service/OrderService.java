package com.example.javatspfci.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javatspfci.code.entity.po.Order;
import com.example.javatspfci.code.entity.vo.CountMsg;
import com.example.javatspfci.code.entity.vo.OrderQueryMsg;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZCL
 * @since 2022-05-11
 */
public interface OrderService extends IService<Order> {

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
    public Boolean createOrder(String orderId, String creatorId, String receiverId, String info, String remark, String type, BigDecimal price, Integer status);

    /**
     * 设置订单配送员
     * @param orderId 订单ID
     * @param deliveryId 配送员ID
     * @return
     */
    public Boolean setOrderDelivery(String orderId, String deliveryId);

    /**
     * 更订单状态
     * @param orderId 订单ID
     * @param statusCode 订单状态
     * @return
     */
    public Boolean updateOrderStatus(String orderId, Integer statusCode);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param reason 原因
     * @return
     */
    public Boolean orderCancel(String orderId,String reason);

    /**
     * 根据店家ID和厂家ID取消订单
     * @param factoryId 厂家Id
     * @param storeId 店家Id
     * @return
     */
    public Boolean orderCancelByFactoryAndStore(String factoryId, String storeId);

    /**
     * 根据配送员Id取消订单
     * @param deliveryId 配送员Id
     * @return
     */
    public Boolean orderCancelByDelivery(String deliveryId);

    /**
     * 店家查询订单
     * @param storeId 店家ID
     * @return
     */
    public List<OrderQueryMsg> listOrderByStore(String storeId);

    /**
     * 厂家查询订单
     * @param factoryId 厂家ID
     * @return
     */
    public List<OrderQueryMsg> listOrderByFactory(String factoryId);

    /**
     * 配送员查询订单
     * @param deliveryId 配送员ID
     * @return
     */
    public List<OrderQueryMsg> listOrderByDelivery(String deliveryId);

    /**
     * 查询单个订单
     * @param orderId 订单ID
     * @return
     */
    public OrderQueryMsg queryOneOrder(String orderId);

    /**
     * 查询所有订单数
     * @return
     */
    public Integer queryAllOrderCount();

    /**
     * 查询月交易额
     * @param firstMonthDay 月初时间
     * @param lastMonthDay 月末时间
     * @return
     */
    public BigDecimal queryMonthPay(String factoryId, LocalDateTime firstMonthDay, LocalDateTime lastMonthDay);

    /**
     * 查询月订单
     * @param firstMonthDay 月初时间
     * @param lastMonthDay 月末时间
     * @return
     */
    public Integer queryMonthOrder(String factoryId, LocalDateTime firstMonthDay, LocalDateTime lastMonthDay);

}
