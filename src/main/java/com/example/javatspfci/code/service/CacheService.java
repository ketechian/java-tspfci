package com.example.javatspfci.code.service;

import com.example.javatspfci.code.entity.po.Cache;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2022-06-02
 */
public interface CacheService extends IService<Cache> {

    /**
     * 添加缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @param count 数量
     * @return
     */
    public Boolean addCache(String storeId, Integer tablewareId, Integer count);

    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Cache queryOne(String storeId, Integer tablewareId);

    /**
     * 根据ID删除缓存
     * @param storeId 店家ID
     * @return
     */
    public Boolean deleteAll(String storeId);

    /**
     * 通过用户ID查询用户
     * @param storeId 店家ID
     * @return
     */
    public List<Cache> listCacheByStoreId(String storeId);


    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Boolean findCache(String storeId, Integer tablewareId);

    /**
     * 更新用户状态
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Boolean updateCache(String storeId, Integer tablewareId);
}
