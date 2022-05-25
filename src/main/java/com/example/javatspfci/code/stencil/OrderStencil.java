package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.entity.dto.order.OrderInfoDto;
import com.example.javatspfci.code.result.Result;

public interface OrderStencil {

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
    public Result createOrder(String creatorId, String receiverId, Integer value, OrderInfoDto info, String remark, String type, Double price, String path);

    /**
     * 设置订单配送员
     * @param orderId 订单ID
     * @param deliveryId 配送员ID
     * @param path url路径
     * @return
     */
    public Result setOrderDelivery(String orderId, String deliveryId,String path);


    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param statusCode 订单状态
     * @param path url路径
     * @return
     */
    public Result updateOrderStatus(String orderId, Integer statusCode, String path);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param reason 取消原因
     * @param path url路径
     * @return
     */
    public Result orderCancel(String orderId,String reason,String path);

    /**
     * 店家查看订单
     * @param storeId 店家ID
     * @param path url路径
     * @return
     */
    public Result listOrderByStore(String storeId,String path);

    /**
     * 厂家查看订单
     * @param factoryId 厂家ID
     * @param path url路径
     * @return
     */
    public Result listOrderByFactory(String factoryId,String path);

    /**
     * 配送员查看订单
     * @param deliveryId 配送员ID
     * @param path url路径
     * @return
     */
    public Result listOrderByDelivery(String deliveryId,String path);

    /**
     * 通过订单ID查看当个订单
     * @param orderId 订单ID
     * @param path url路径
     * @return
     */
    public Result queryOneOrder(String orderId,String path);

    /**
     * 查询对应厂家信息
     * @param factoryId 厂家Id
     * @param path url路径
     * @return
     */
    public Result queryInfoCountByFactory(String factoryId, String path);
}
