package com.example.javatspfci.code.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/2 14:27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("厂家实体")
public class StoreLoginMsg {
    @ApiModelProperty("店家编号")
    private String stId;

    @ApiModelProperty("账户名")
    private String stUserName;

    @ApiModelProperty("店名")
    private String stStoreName;

    @ApiModelProperty("厂家编号")
    private String stFactory;

    @ApiModelProperty("店主姓名")
    private String stOwnerName;

    @ApiModelProperty("店面介绍")
    private String stStoreIntroduce;

    @ApiModelProperty("电话号码")
    private String stPhone;

    @ApiModelProperty("店面图片")
    private String stPicture;

    @ApiModelProperty("店面地址")
    private String stLocation;

    @ApiModelProperty("经营许可证编号")
    private String stLicence;

    @ApiModelProperty("逻辑删除")
    private Boolean isDelete;

    @ApiModelProperty("店家密码")
    private String password;

    @ApiModelProperty("角色等级")
    private String uRole;

}
