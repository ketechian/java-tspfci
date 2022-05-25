package com.example.javatspfci.code.entity.dto.order;

import com.example.javatspfci.code.entity.dto.SimpTableInfoDto;
import com.example.javatspfci.code.entity.po.Tableware;
import lombok.Data;

import java.util.List;

@Data
public class OrderSetInfoDto {

    /**
     * 套餐信息
     */
    private List<SimpTableInfoDto> tableware;

    /**
     * 套餐数量
     */
    private Integer count;

    /**
     * 套餐总价
     */
    private Double price;

    public String toString(){
        StringBuffer sb = new StringBuffer("{");
        for (SimpTableInfoDto t:tableware) {
            sb.append(t.getName() + ",");
        }
        sb.append("} X " + count + " : " + price + "元");
        return sb.toString();
    }

}
