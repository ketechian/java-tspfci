package com.example.javatspfci.code.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/17 23:14
 */

@Data
public class DefaultDto {
    @ApiModelProperty("默认配餐编号")
    private Integer defId;

    @ApiModelProperty("对应厂家")
    private String defFactoryId;

    @ApiModelProperty("配餐图片路径")
    private String defPicture;

    @ApiModelProperty("配餐价格")
    private BigDecimal defPrice;

    @ApiModelProperty("配餐名")
    private String defName;

    @ApiModelProperty("配餐信息")
    private String defInfo;

    @ApiModelProperty("套件库存")
    private Integer defStock;
}
