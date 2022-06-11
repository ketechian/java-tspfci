package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.result.Result;

public interface CacheStencil {

    /**
     * 添加缓存
     * @param storeId 店家ID
     * @param tablewareId 餐具ID
     * @param count 数量
     * @param path url路径
     * @return
     */
    public Result addCache(String storeId, Integer tablewareId, Integer count, String path);

}
