package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/4 20:38
 */

public interface  StoreStencil {
    /**
     * 根据id查询店家信息
     * @param id 店家id
     * @param path 请求路径
     * @return
     */
    public Result getOneStoreById(String id, String path);

    /**
     * 获取全部店家
     * @param page 页数
     * @param count 查询数量
     * @param path url路径
     * @return
     */
    public Result getAllStoreByPage(String factoryId, Integer page, Integer count, String path);

    /**
     * 更新店家信息
     * @param id 店家ID
     * @param ownerName 店主姓名
     * @param storeName 店名
     * @param introduce 店家介绍
     * @param headPicture 店面图片
     * @param location 店家地址
     * @param licence 经营许可证图片
     * @param path url路径
     * @return
     */
    public Result updateStoreInfo(String id, String username, String ownerName, String storeName,
                                  String introduce, MultipartFile headPicture, String location, MultipartFile licence, String path) throws IOException;

    /**
     * 商家添加合作厂家
     * @param storeId 商家ID
     * @param factoryId 厂家ID
     * @param path url路径
     * @return
     */
    public Result addCooperation(String storeId,String factoryId, String path);

    /**
     * 根据厂家Id添加用户信息
     * @param factoryId 厂家Id
     * @param path url路径
     * @return
     */
    public Result listStoreByFactoryId(String factoryId, String path);

    /**
     * 取消合作
     * @param factoryId 厂家ID
     * @param storeId 店家
     * @param path url路径
     * @return
     */
    public Result removeCooperation(String factoryId, String storeId,String path);
}
