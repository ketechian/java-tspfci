package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.po.Delivery;
import com.example.javatspfci.code.entity.vo.DeliverQueryMsg;
import com.example.javatspfci.code.entity.vo.DeliveryLoginMsg;
import com.example.javatspfci.code.mapper.DeliveryMapper;
import com.example.javatspfci.code.service.DeliveryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;

    /**
     * 添加配送员
     * @param id 配送员用户id
     * @param username 用户名
     * @param phone 电话号码
     * @return
     */
    @Override
    public Boolean addDelivery(String id, String username, String phone, String factoryId) {
        return deliveryMapper.addDelivery(id, username, phone, factoryId) == 1;
    }

    /**
     * 通过手机查询人数
     * @param phone 手机号码
     * @return
     */
    @Override
    public Boolean queryCountByPhone(String phone) {
        return deliveryMapper.queryCountByPhone(phone) == 1;
    }

    /**
     * 查询是否存在用户名相同
     * @param name 用户名
     * @return
     */
    @Override
    public String queryCountByName(String name) {
        return deliveryMapper.queryCountByName(name);
    }

    /**
     *
     * @param dePhone 配送员手机号
     * @param password 密码
     * @return
     */
    @Override
    public DeliveryLoginMsg deliveryLogin(String dePhone, String password) {
        return deliveryMapper.selectDeByPhone(dePhone, password);
    }

    /**
     * 分页查询配送员
     * @param start 开始位置
     * @param count 查询人数
     * @return
     */
    @Override
    public List<Delivery> listAllDeliverByPage(Integer start, Integer count) {
        return deliveryMapper.listAllDeliverByPage(start, count);
    }

    /**
     * 查询所有配送员人数
     * @return
     */
    @Override
    public Integer queryAllDeliverCount() {
        return deliveryMapper.queryAllDeliverCount();
    }

    /**
     * 通过ID查询配送员
     * @param delID 配送员ID
     * @return
     */
    @Override
    public Delivery getOneDeliveryByID(String delID) {
        return deliveryMapper.getOneDeliveryByID(delID);
    }

    /**
     * 通过厂家ID获取厂家数量
     * @param factoryId 工厂ID
     * @return
     */
    @Override
    public Integer queryDeliveryCountByFactoryId(String factoryId) {
        return deliveryMapper.queryDeliveryCountByFactoryId(factoryId);
    }

    /**
     * 通过工厂ID获取配送员
     * @param factoryId 工厂ID
     * @param start 页数
     * @param count 查询数据数量
     * @return
     */
    @Override
    public List<Delivery> listDeliveryByFactoryId(String factoryId, Integer start, Integer count) {
        return deliveryMapper.listDeliveryByFactoryId(factoryId, start, count);
    }

    /**
     * 更新配送员信息
     * @param delID 配送员ID
     * @param username 用户名
     * @param headPicture 头像图片
     * @param drivingLicence 车辆行驶证图片
     * @param carLicence 驾驶证图片
     * @param carCode 车牌号图片
     * @return
     */
    @Override
    public Boolean updateDelivery(String delID, String username, String headPicture,String drivingLicence,
                                  String carLicence, String carCode) {
        return deliveryMapper.updateDelivery(delID, username, headPicture, drivingLicence, carLicence, carCode) == 1;
    }

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @return
     */
    @Override
    public List<Delivery> queryFindDelivery(String username) {
        return deliveryMapper.queryFindDelivery(username);
    }

}
