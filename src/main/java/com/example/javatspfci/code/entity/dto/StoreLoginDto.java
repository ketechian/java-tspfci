package com.example.javatspfci.code.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/2 14:30
 */
@Data
public class StoreLoginDto {

    /**
     * 店家电话
     */
    private String stPhone;

    /**
     * 店家密码
     */
    private String password;
}
