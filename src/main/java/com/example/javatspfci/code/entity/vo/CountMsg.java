package com.example.javatspfci.code.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountMsg {

    /**
     * 厂家数
     */
    private Integer factoryCount;

    /**
     * 配送员数
     */
    private Integer deliveryCount;

    /**
     * 店家数
     */
    private Integer storeCount;

    /**
     * 订单数
     */
    private Integer orderCount;

}
