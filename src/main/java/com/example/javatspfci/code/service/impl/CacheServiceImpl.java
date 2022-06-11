package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.po.Cache;
import com.example.javatspfci.code.mapper.CacheMapper;
import com.example.javatspfci.code.service.CacheService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-06-02
 */
@Service
public class CacheServiceImpl extends ServiceImpl<CacheMapper, Cache> implements CacheService {

    @Resource
    private CacheMapper cacheMapper;

    /**
     * 添加缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @param count 数量
     * @return
     */
    @Override
    public Boolean addCache(String storeId, Integer tablewareId, Integer count) {
        return cacheMapper.addCache(storeId, tablewareId, count) == 1;
    }

    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    @Override
    public Cache queryOne(String storeId, Integer tablewareId) {
        return cacheMapper.queryOne(storeId, tablewareId);
    }

    /**
     * 根据ID删除缓存
     * @param storeId 店家ID
     * @return
     */
    @Override
    public Boolean deleteAll(String storeId) {
        return cacheMapper.deleteAll(storeId) == 1;
    }

    /**
     * 通过用户ID查询用户
     * @param storeId 店家ID
     * @return
     */
    @Override
    public List<Cache> listCacheByStoreId(String storeId) {
        return cacheMapper.listCacheByStoreId(storeId);
    }

    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    @Override
    public Boolean findCache(String storeId, Integer tablewareId) {
        return cacheMapper.findCache(storeId, tablewareId) == 1;
    }

    /**
     * 更新用户状态
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    @Override
    public Boolean updateCache(String storeId, Integer tablewareId) {
        return cacheMapper.updateCache(storeId, tablewareId) == 1;
    }
}
