package com.example.javatspfci.code.entity.dto;

import lombok.Data;

/**
 * 店家添加合作厂家
 */
@Data
public class StoreAddCooperationDto {

    /**
     * 店家Id
     */
    private String storeId;

    /**
     * 厂家Id
     */
    private String factoryId;

}
