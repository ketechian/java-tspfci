package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.dto.order.OrderInfoDto;
import com.example.javatspfci.code.entity.vo.CountMsg;
import com.example.javatspfci.code.entity.vo.OrderQueryMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.DeliveryService;
import com.example.javatspfci.code.service.OrderService;
import com.example.javatspfci.code.service.StoreService;
import com.example.javatspfci.code.stencil.OrderStencil;
import com.example.javatspfci.code.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderStencilImpl implements OrderStencil {

    @Resource
    private OrderService orderService;

    @Resource
    private StoreService storeService;

    @Resource
    private DeliveryService deliveryService;

    /**
     * 创建订单
     * @param creatorId 店家ID
     * @param receiverId 厂家ID
     * @param value 权值
     * @param info 订单内容
     * @param remark 备注
     * @param type 订单类型
     * @param price 订单价格
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result createOrder(String creatorId, String receiverId, Integer value, OrderInfoDto info, String remark, String type, Double price, String path) {
        Map<String, Object> message = new HashMap<>();
        int createCode = 1;
        //生成UUID
        String id = UUIDUtil.getUUID();
        //初始订单状态为0
        Integer orderStatus = 0;
        //转化数据类型
        BigDecimal bigPrice = new BigDecimal(price);
        boolean createJudge = orderService.createOrder(id, creatorId, receiverId, info.toString(), remark, type, bigPrice, orderStatus);
        //判断是否添加成功
        if (!createJudge){
            createCode = 0;
        }
        message.put("create_code",createCode);
        return new Result().result200(message, path);
    }

    /**
     * 指派配送员
     * @param orderId 订单ID
     * @param deliveryId 配送员ID
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result setOrderDelivery(String orderId, String deliveryId, String path) {
        Map<String, Object> message = new HashMap<>();
        int updateCode = 1;
        boolean updateJudge = orderService.setOrderDelivery(orderId, deliveryId);
        if (!updateJudge){
            updateCode = 0;
        }
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }

    /**
     * 更改订单状态
     * @param orderId 订单ID
     * @param statusCode 订单状态
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateOrderStatus(String orderId, Integer statusCode, String path) {
        Map<String, Object> message = new HashMap<>();
        int updateCode = 1;
        boolean updateJudge = orderService.updateOrderStatus(orderId, statusCode);
        if (!updateJudge){
            updateCode = 0;
        }
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param reason 取消原因
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result orderCancel(String orderId, String reason, String path) {
        Map<String, Object> message = new HashMap<>();
        int updateCode = 1;
        boolean updateJudge = orderService.orderCancel(orderId, reason);
        if (!updateJudge){
            updateCode = 0;
        }
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }

    /**
     * 通过店家ID查询订单信息
     * @param storeId 店家ID
     * @param path url路径
     * @return
     */
    @Override
    public Result listOrderByStore(String storeId, String path) {
        Map<String, Object> message = new HashMap<>();
        //获取数据
        List<OrderQueryMsg> data = orderService.listOrderByStore(storeId);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 通过厂家ID查询订单信息
     * @param factoryId 厂家ID
     * @param path url路径
     * @return
     */
    @Override
    public Result listOrderByFactory(String factoryId, String path) {
        Map<String, Object> message = new HashMap<>();
        //获取数据
        List<OrderQueryMsg> data = orderService.listOrderByFactory(factoryId);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 通过配送员ID查询订单信息
     * @param deliveryId 配送员ID
     * @param path url路径
     * @return
     */
    @Override
    public Result listOrderByDelivery(String deliveryId, String path) {
        Map<String, Object> message = new HashMap<>();
        //获取数据
        List<OrderQueryMsg> data = orderService.listOrderByDelivery(deliveryId);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 查询单个订单信息
     * @param orderId 订单ID
     * @param path url路径
     * @return
     */
    @Override
    public Result queryOneOrder(String orderId, String path) {
        Map<String, Object> message = new HashMap<>();
        //获取数据
        OrderQueryMsg data = orderService.queryOneOrder(orderId);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 查询对应厂家信息
     * @param factoryId 厂家Id
     * @param path url路径
     * @return
     */
    @Override
    public Result queryInfoCountByFactory(String factoryId, String path) {
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime firstMonth = dateNow.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastMonth = dateNow.with(TemporalAdjusters.lastDayOfMonth());
        BigDecimal totalPrice = orderService.queryMonthPay(factoryId,firstMonth, lastMonth);
        Integer totalOrder = orderService.queryMonthOrder(factoryId, firstMonth, lastMonth);
        Integer totalStore = storeService.queryStoreCountByFactoryId(factoryId);
        Integer totalDelivery = deliveryService.queryDeliveryCountByFactoryId(factoryId);
        CountMsg countMsg = new CountMsg(totalPrice, totalDelivery, totalStore, totalOrder);
        Map<String, Object> message = new HashMap<>();
        message.put("data",countMsg);
        return new Result().result200(message, path);
    }

}
