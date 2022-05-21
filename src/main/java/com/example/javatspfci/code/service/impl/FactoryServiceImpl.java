package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.po.Factory;
import com.example.javatspfci.code.entity.vo.DeliverQueryMsg;
import com.example.javatspfci.code.entity.vo.FactoryLoginMsg;
import com.example.javatspfci.code.entity.vo.FactoryQueryMsg;
import com.example.javatspfci.code.mapper.FactoryMapper;
import com.example.javatspfci.code.service.FactoryService;
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
 * @since 2022-04-29
 */
@Service
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements FactoryService {

    @Resource
    private FactoryMapper factoryMapper;

    /**
     * 添加厂家
     * @param id 厂家用户主键
     * @param username 用户名
     * @param phone 电话
     * @return
     */
    @Override
    public Boolean addFactory(String id, String username, String phone) {
        return factoryMapper.addFactory(id, username, phone) == 1;
    }

    @Override
    public Boolean queryCountByPhone(String phone) {
        return factoryMapper.queryCountByPhone(phone) == 1;
    }

    /**
     * 查询是否存在用户名相同
     * @param name 用户名
     * @return
     */
    @Override
    public String queryCountByName(String name) {
        return factoryMapper.queryCountByName(name);
    }

    /**
     * 厂家登录
     * @param facPhone 厂家电话
     * @param password 密码
     * @return
     */
    @Override
    public FactoryLoginMsg factoryLogin(String facPhone, String password) {
        return factoryMapper.selectFacPhone(facPhone, password);
    }

    /**
     * 分页查询所有厂家
     * @param start 开始位置
     * @param count 查询人数
     * @return
     */
    @Override
    public List<Factory> listAllFactoryByPage(Integer start, Integer count) {
        return factoryMapper.listAllFactoryByPage(start, count);
    }

    /**
     * 查询所有厂家个数
     * @return
     */
    @Override
    public Integer queryAllFactoryCount() {
        return factoryMapper.queryAllFactoryCount();
    }

    /**
     * 查询单个厂家
     * @param delID 配送员ID
     * @return
     */
    @Override
    public FactoryQueryMsg getOneFactoryByID(String delID) {
        return factoryMapper.getOneFactoryByID(delID);
    }

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
    @Override
    public Boolean updateFactory(String id, String username, String factoryName,
                                 String introduce, String headPicture, String location, String factoryLicence) {
        return factoryMapper.updateFactory(id, username, factoryName, introduce, headPicture, location, factoryLicence) == 1;
    }

}
