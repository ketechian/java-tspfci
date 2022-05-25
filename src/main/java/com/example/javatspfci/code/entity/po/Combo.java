package com.example.javatspfci.code.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("tb_combo")
@ApiModel(value = "Combo对象", description = "")
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("默认配餐编号")
    @TableId(value = "com_id", type = IdType.AUTO)
    private Integer comId;

    @ApiModelProperty("对应厂家")
    @TableField("com_factory")
    private String comFactory;

    @ApiModelProperty("对应店家")
    @TableField("com_store")
    private String comStore;

    @ApiModelProperty("配餐价格")
    @TableField("com_price")
    private BigDecimal comPrice;

    @ApiModelProperty("配餐名")
    @TableField("com_name")
    private String comName;

    @ApiModelProperty("配餐信息")
    @TableField("com_info")
    private String comInfo;

    @ApiModelProperty("逻辑删除")
    @TableField("is_delete")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
