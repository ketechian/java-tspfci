package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.po.Combo;
import com.example.javatspfci.code.mapper.ComboMapper;
import com.example.javatspfci.code.service.ComboService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-05-23
 */
@Service
public class ComboServiceImpl extends ServiceImpl<ComboMapper, Combo> implements ComboService {

    @Resource
    private ComboMapper comboMapper;

    /**
     * 添加组合
     * @param factoryId 厂家ID
     * @param storeId 店家ID
     * @param price 价格
     * @param name 姓名
     * @param info 配套信息
     */
    @Override
    public Boolean addStoreCombo(String factoryId, String storeId, BigDecimal price, String name, String info) {
        return comboMapper.addStoreCombo(factoryId, storeId, price, name, info) == 1;
    }

    /**
     * 查看组合
     * @param storeId 店家ID
     * @param factoryId 厂家ID
     */
    @Override
    public List<Combo> listComboByStore(String storeId, String factoryId) {
        return comboMapper.listComboByStore(storeId, factoryId);
    }

    /**
     * 删除组合
     * @param comboId 配套ID
     */
    @Override
    public Boolean deleteStoreCombo(Integer comboId) {
        return comboMapper.deleteStoreCombo(comboId) == 1;
    }
}
