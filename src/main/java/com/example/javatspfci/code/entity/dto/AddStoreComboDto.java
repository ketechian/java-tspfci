package com.example.javatspfci.code.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddStoreComboDto {

    /**
     * 厂家Id
     */
    private String factoryId;

    /**
     * 店家Id
     */
    private String storeId;

    /**
     * 套餐名
     */
        private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 配套信息
     */
    private List<SimpTableInfoDto> info;

}
