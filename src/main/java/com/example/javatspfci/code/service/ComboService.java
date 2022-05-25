package com.example.javatspfci.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javatspfci.code.entity.po.Combo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-05-23
 */
public interface ComboService extends IService<Combo> {

    /**
     * 添加组合
     * @param factoryId 厂家ID
     * @param storeId 店家ID
     * @param price 价格
     * @param name 姓名
     * @param info 配套信息
     */
    public Boolean addStoreCombo(String factoryId, String storeId, BigDecimal price, String name, String info);

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
    public Boolean deleteStoreCombo(Integer comboId);
}
