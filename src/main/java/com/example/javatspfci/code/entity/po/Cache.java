package com.example.javatspfci.code.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-06-02
 */
@Getter
@Setter
@TableName("tb_cache")
@ApiModel(value = "Cache对象", description = "")
public class Cache implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("默认配餐编号")
    @TableId(value = "cac_id", type = IdType.AUTO)
    private Integer cacId;

    @ApiModelProperty("对应店家")
    @TableField("cac_store")
    private String cacStore;

    @ApiModelProperty("餐具ID")
    @TableField("cac_tableware")
    private Integer cacTableware;

    @ApiModelProperty("状态")
    @TableField("cac_status")
    private Boolean cacStatus;

    @ApiModelProperty("数量")
    @TableField("count")
    private Integer count;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
