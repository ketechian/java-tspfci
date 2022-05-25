package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.order.CreateOrderDto;
import com.example.javatspfci.code.entity.dto.order.OrderCancelDto;
import com.example.javatspfci.code.entity.dto.order.SetOrderDeliveryDto;
import com.example.javatspfci.code.entity.dto.order.UpdateOrderStatusDto;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.stencil.OrderStencil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZCL
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/code/order")
public class OrderController {

    @Resource
    private OrderStencil orderStencil;

    /**
     * 创建订单
     * @param createOrderDto 后端创建订单接收类
     * @return
     */
    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody CreateOrderDto createOrderDto){
        return orderStencil.createOrder(
                createOrderDto.getCreatorId(),
                createOrderDto.getReceiverId(),
                createOrderDto.getValue(),
                createOrderDto.getInfo(),
                createOrderDto.getRemark(),
                createOrderDto.getType(),
                createOrderDto.getPrice(),"/code/order/createOrder");
    }

    /**
     * 设置配送员
     * @param setOrderDeliveryDto 设置配送员接收类
     * @return
     */
    @PostMapping("/setOrderDelivery")
    public Result setOrderDelivery(@RequestBody SetOrderDeliveryDto setOrderDeliveryDto){
        return orderStencil.setOrderDelivery(
                setOrderDeliveryDto.getOrderId(),
                setOrderDeliveryDto.getDeliveryId(), "/code/order/setOrderDelivery");
    }

    /**
     * 更新订单状态
     * @param updateOrderStatusDto 更新订单状态接收类
     * @return
     */
    @PostMapping("/updateOrderStatus")
    public Result updateOrderStatus(@RequestBody UpdateOrderStatusDto updateOrderStatusDto){
        return orderStencil.updateOrderStatus(
                updateOrderStatusDto.getOrderId(),
                updateOrderStatusDto.getOrderStatus(), "/code/order/updateOrderStatus");
    }

    /**
     * 取消订单
     * @param orderCancelDto 取消订单接收类
     * @return
     */
    @PostMapping("/orderCancel")
    public Result orderCancel(@RequestBody OrderCancelDto orderCancelDto){
        return orderStencil.orderCancel(
                orderCancelDto.getOrderId(),
                orderCancelDto.getReason(), "/code/order/orderCancel");
    }

    /**
     * 根据店家ID获取订单数据
     * @param storeId 店家Id
     * @return
     */
    @GetMapping("/listOrderByStore")
    public Result listOrderByStore(@RequestParam String storeId){
        return orderStencil.listOrderByStore(storeId,"/code/order/listOrderByStore");
    }

    /**
     * 根据厂家ID获取订单数据
     * @param factoryId 厂家Id
     * @return
     */
    @GetMapping("/listOrderByFactory")
    public Result listOrderByFactory(@RequestParam String factoryId){
        return orderStencil.listOrderByFactory(factoryId, "/code/order/listOrderByFactory");
    }

    /**
     * 根据店家ID获取订单数据
     * @param deliveryId 配送员Id
     * @return
     */
    @GetMapping("/listOrderByDelivery")
    public Result listOrderByDelivery(@RequestParam String deliveryId){
        return orderStencil.listOrderByDelivery(deliveryId,"/code/order/listOrderByDelivery");
    }

    /**
     * 根据订单ID获取单个订单数据
     * @param orderId 订单Id
     * @return
     */
    @GetMapping("/queryOneOrder")
    public Result queryOneOrder(@RequestParam String orderId){
        return orderStencil.queryOneOrder(orderId, "/code/order/queryOneOrder");
    }

    /**
     * 查询对应厂家信息
     * @param factoryId 厂家Id
     * @return
     */
    @GetMapping("/queryInfoCountByFactory")
    public Result queryInfoCountByFactory(@RequestParam String factoryId){
        return orderStencil.queryInfoCountByFactory(factoryId, "/code/factoryId/queryInfoCountByFactory");
    }
}
