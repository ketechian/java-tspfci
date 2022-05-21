package com.example.javatspfci.code.service;

import com.example.javatspfci.code.entity.po.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javatspfci.code.entity.vo.StoreLoginMsg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
public interface StoreService extends IService<Store> {
    /**
     *
     * @param id 店家用户主键
     * @param username 用户名
     * @param phone 电话
     * @return
     */
    public Boolean addStore(String id, String username, String phone);

    /**
     * 查询手机是否重复
     * @param phone 手机号码
     * @return
     */
    public Boolean queryCountByPhone(String phone);

    /**
     * 查询是否存在用户名相同
     * @param name 用户名
     * @return
     */
    public String queryCountByName(String name);

    /**
     * 店家登录
     * @param stPhone 店家手机号
     * @param password 密码
     * @return
     */
    public StoreLoginMsg storeLogin(String stPhone, String password);

    /**
     * 根据id查询店家信息
     * @param id
     * @return
     */
    public StoreLoginMsg queryStoreById(String id);

    /**
     * 分页查询店家信息
     * @param start 开始位置
     * @param count 查询个数
     * @return
     */
    public List<Store> listAllStoreByPage(Integer start,Integer count);

    /**
     * 查询店家数
     * @return
     */
    public Integer queryAllStoreCount();

    /**
     * 更新店家信息
     * @param id 店家ID
     * @param ownerName 店主姓名
     * @param storeName 店名
     * @param introduce 店家介绍
     * @param headPicture 店面图片
     * @param location 店家地址
     * @param licence 经营许可证图片
     * @return
     */
    public Boolean updateStoreInfo(String id, String username, String ownerName, String storeName,
                                   String introduce, String headPicture, String location, String licence);
}
