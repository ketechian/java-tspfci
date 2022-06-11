package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.po.Cache;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.CacheService;
import com.example.javatspfci.code.stencil.CacheStencil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CacheStencilImpl implements CacheStencil {

    @Resource
    private CacheService cacheService;

    /**
     * 添加缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @param count 数量
     * @param path url路径
     * @return
     */
    @Override
    public Result addCache(String storeId, Integer tablewareId, Integer count, String path) {
        if (!cacheService.findCache(storeId, tablewareId)){
            cacheService.addCache(storeId, tablewareId, count);
        } else {
            cacheService.updateCache(storeId, tablewareId);
        }
        Map<String, Object> message = new HashMap<>();
        List<Cache> data = cacheService.listCacheByStoreId(storeId);
        message.put("data", data);
        return new Result().result200(message, path);
    }

}
