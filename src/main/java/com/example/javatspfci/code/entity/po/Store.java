package com.example.javatspfci.code.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@Data
@TableName("tb_store")
@ApiModel(value = "Store对象", description = "")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("店家编号")
    @TableId("st_id")
    private String stId;

    @ApiModelProperty("账户名")
    @TableField("st_user_name")
    private String stUserName;

    @ApiModelProperty("店名")
    @TableField("st_factory")
    private String stFactory;

    @ApiModelProperty("店名")
    @TableField("st_store_name")
    private String stStoreName;

    @ApiModelProperty("店主姓名")
    @TableField("st_owner_name")
    private String stOwnerName;

    @ApiModelProperty("店面介绍")
    @TableField("st_store_introduce")
    private String stStoreIntroduce;

    @ApiModelProperty("电话号码")
    @TableField("st_phone")
    private String stPhone;

    @ApiModelProperty("店面图片")
    @TableField("st_picture")
    private String stPicture;

    @ApiModelProperty("店面地址")
    @TableField("st_location")
    private String stLocation;

    @ApiModelProperty("经营许可证编号")
    @TableField("st_licence")
    private String stLicence;

    @ApiModelProperty("逻辑删除")
    @TableField("is_delete")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
