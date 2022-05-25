package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DeliveryStencil {

    /**
     * 获取全部配送员
     * @param page 页数
     * @param count 查询数量
     * @param path url路径
     * @return
     */
    public Result getAllDeliveryByPage(Integer page,Integer count,String path);

    /**
     * 通过工厂ID获取配送员
     * @param factoryId 工厂ID
     * @param page 页数
     * @param count 查询数据数量
     * @param path url路径
     * @return
     */
    public Result listDeliveryByFactory(String factoryId,Integer page, Integer count, String path);

    /**
     * 通过id获取单个配送员信息
     * @param delID 配送员ID
     * @param path url路径
     * @return
     */
    public Result getOneDeliveryByID(String delID, String path);

    /**
     * 更新配送员信息
     * @param delID 配送员ID
     * @param username 用户名
     * @param headPicture 头像图片
     * @param drivingLicence 车辆行驶证图片
     * @param carLicence 驾驶证图片
     * @param carCode 车牌号图片
     * @param path url路径
     * @return
     */
    public Result updateDeliveryInfo(String delID, String username, MultipartFile headPicture, MultipartFile drivingLicence,
                                     MultipartFile carLicence, String carCode, String path) throws IOException;

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @param path url路径
     * @return
     */
    public Result queryFindDelivery(String username,String path);

    /**
     * 删除配送员
     * @param deliveryId 配送员Id
     * @param path url路径
     * @return
     */
    public Result deleteDelivery(String deliveryId, String path);
}
