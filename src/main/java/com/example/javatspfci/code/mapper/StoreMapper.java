package com.example.javatspfci.code.mapper;

import com.example.javatspfci.code.entity.po.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javatspfci.code.entity.vo.StoreLoginMsg;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@Mapper
@Repository
public interface StoreMapper extends BaseMapper<Store> {

    /**
     *
     * @param id 店家用户主键
     * @param username 用户名
     * @param phone 电话
     * @return
     */
    public Integer addStore(String id, String username, String phone);

    /**
     * 查询手机是否重复
     * @param phone 手机号码
     * @return
     */
    public Integer queryCountByPhone(String phone);

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
    public StoreLoginMsg selectStoreByPhone(String stPhone, String password);

    /**
     * 通过id查询店家
     * @param id
     * @return
     */
    public StoreLoginMsg selectStoreUserById(String id);

    /**
     * 分页查询店家信息
     * @param start 开始位置
     * @param count 查多少个
     * @return
     */
    public List<Store> listAllStoreByPage(String factoryId, Integer start, Integer count);

    /**
     * 查询店家数
     * @return
     */
    public Integer queryAllStoreCount();

    /**
     * 更新店家信息
     * @param id 店家ID
     * @param username
     * @param ownerName 店主姓名
     * @param storeName 店名
     * @param introduce 店家介绍
     * @param headPicture 店面图片
     * @param location 店家地址
     * @param licence 经营许可证图片
     * @return
     */
    public Integer updateStoreInfo(String id, String username, String ownerName, String storeName,
                                   String introduce, String headPicture, String location, String licence);

    /**
     * 添加合作
     * @param storeId 店家Id
     * @param factoryId 厂家Id
     * @return
     */
    public Integer addCooperation(String storeId, String factoryId);

    /**
     * 根据厂家Id获取用户信息
     * @param factoryId 厂家Id
     * @return
     */
    public List<Store> listStoreByFactoryId(String factoryId);

    /**
     * 取消合作
     * @param factoryId 厂家Id
     * @param storeId 店家Id
     * @return
     */
    public Integer deleteCooperation(String factoryId, String storeId);

    /**
     * 根据厂家Id获取用户
     * @param factoryId 厂家Id
     * @return
     */
    public Integer queryStoreCountByFactoryId(String factoryId);
}
