package com.example.javatspfci.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javatspfci.code.entity.po.Combo;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-05-23
 */
@Mapper
public interface ComboMapper extends BaseMapper<Combo> {

    /**
     * 添加组合
     * @param factoryId 厂家ID
     * @param storeId 店家ID
     * @param price 价格
     * @param name 姓名
     * @param info 配套信息
     */
    public Integer addStoreCombo(String factoryId, String storeId, BigDecimal price, String name, String info);

    /**
     * 查看组合
     * @param storeId 店家ID
     * @param factoryId 厂家ID
     */
    public List<Combo> listComboByStore(String storeId, String factoryId);

    /**
     * 删除组合
     * @param comboId 配套ID
     */
    public Integer deleteStoreCombo(Integer comboId);

}
