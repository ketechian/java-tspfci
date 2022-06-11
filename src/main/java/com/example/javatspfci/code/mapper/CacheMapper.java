package com.example.javatspfci.code.mapper;

import com.example.javatspfci.code.entity.po.Cache;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-06-02
 */
@Repository
@Mapper
public interface CacheMapper extends BaseMapper<Cache> {

    /**
     * 添加缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @param count 数量
     * @return
     */
    public Integer addCache(String storeId, Integer tablewareId, Integer count);

    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Cache queryOne(String storeId, Integer tablewareId);

    /**
     * 查询是否已存在缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Integer findCache(String storeId, Integer tablewareId);

    /**
     * 通过用户ID查询用户
     * @param storeId 店家ID
     * @return
     */
    public List<Cache> listCacheByStoreId(String storeId);

    /**
     * 更新用户状态
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @return
     */
    public Integer updateCache(String storeId, Integer tablewareId);

    /**
     * 根据ID删除缓存
     * @param storeId 店家ID
     * @return
     */
    public Integer deleteAll(String storeId);

}
