package com.example.javatspfci.code.entity.dto.order;

import lombok.Data;

@Data
public class CreateOrderDto {

    /**
     * 店家ID
     */
    private String creatorId;

    /**
     * 厂家ID
     */
    private String receiverId;

    /**
     * 权值
     */
    private Integer value;

    /**
     * 订单信息
     */
    private String info;

    /**
     * 订单备注信息
     */
    private String remark;

    /**
     * 订单类型
     */
    private String type;

    /**
     * 订单价格
     */
    private Double price;

}
