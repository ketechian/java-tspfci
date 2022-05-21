package com.example.javatspfci.code.service;

import com.example.javatspfci.code.entity.po.Delivery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javatspfci.code.entity.vo.DeliverQueryMsg;
import com.example.javatspfci.code.entity.vo.DeliveryLoginMsg;
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
public interface DeliveryService extends IService<Delivery> {

    /**
     * 添加配送员
     * @param id 配送员用户id
     * @param username 用户名
     * @param phone 电话号码
     * @return
     */
    public Boolean addDelivery(String id, String username, String phone, String factoryId);

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
     *
     * @param dePhone 配送员账号
     * @param password 密码
     * @return
     */
    public DeliveryLoginMsg deliveryLogin(String dePhone, String password);

    /**
     * 分页查询配送员
     * @param start 开始位置
     * @param count 查询人数
     * @return
     */
    public List<Delivery> listAllDeliverByPage(Integer start,Integer count);

    /**
     * 查询配送员人数
     * @return
     */
    public Integer queryAllDeliverCount();

    /**
     * 通过ID查询配送员
     * @param delID 配送员ID
     * @return
     */
    public Delivery getOneDeliveryByID(String delID);

    /**
     * 通过厂家ID获取配送员数量
     * @param factoryId 工厂ID
     * @return
     */
    public Integer queryDeliveryCountByFactoryId(String factoryId);

    /**
     * 通过工厂ID获取配送员
     * @param factoryId 工厂ID
     * @param page 页数
     * @param count 查询数据数量
     * @return
     */
    public List<Delivery> listDeliveryByFactoryId(String factoryId, Integer start, Integer count);

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
    public Boolean updateDelivery(String delID, String username, String headPicture, String drivingLicence,
                                  String carLicence, String carCode);

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @return
     */
    public List<Delivery> queryFindDelivery(String username);
}
