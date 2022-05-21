package com.example.javatspfci.code.mapper;

import com.example.javatspfci.code.entity.po.Factory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javatspfci.code.entity.vo.DeliverQueryMsg;
import com.example.javatspfci.code.entity.vo.FactoryLoginMsg;
import com.example.javatspfci.code.entity.vo.FactoryQueryMsg;
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
public interface FactoryMapper extends BaseMapper<Factory> {

    /**
     *
     * @param id 厂家用户主键
     * @param username 用户名
     * @param phone 电话
     * @return
     */
    public Integer addFactory(String id, String username, String phone);

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
     *
     * @param facPhone 厂家电话
     * @param password 密码
     * @return
     */
    public FactoryLoginMsg selectFacPhone(String facPhone, String password);

    /**
     * 分页查询厂家
     * @param start 开始位置
     * @param count 查询人数
     * @return
     */
    public List<Factory> listAllFactoryByPage(Integer start, Integer count);

    /**
     * 查询厂家人数
     * @return
     */
    public Integer queryAllFactoryCount();

    /**
     * 通过ID查询厂家
     * @param delID 配送员ID
     * @return
     */
    public FactoryQueryMsg getOneFactoryByID(String delID);

    /**
     * 修改厂家
     * @param id 厂家id
     * @param username 用户名
     * @param factoryName 厂家名
     * @param introduce 厂家介绍
     * @param headPicture 厂家图片
     * @param location 厂家地址
     * @param factoryLicence 经营许可证
     * @return
     */
    public Integer updateFactory(String id, String username, String factoryName,
                                 String introduce, String headPicture, String location, String factoryLicence);

}
