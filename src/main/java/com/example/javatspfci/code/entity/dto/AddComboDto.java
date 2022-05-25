package com.example.javatspfci.code.entity.dto;

import com.example.javatspfci.code.entity.po.Tableware;
import lombok.Data;

import java.util.List;

@Data
public class AddComboDto {

    /**
     * 厂家Id
     */
    private String factoryId;

    /**
     * 店家Id
     */
    private String storeId;

    /**
     * 价格
     */
    private Double price;

    /**
     * 姓名
     */
    private String name;

    /**
     * 配套信息
     */
    private List<Tableware> info;

}
