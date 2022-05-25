package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Delivery;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.DeliveryService;
import com.example.javatspfci.code.service.OrderService;
import com.example.javatspfci.code.stencil.DeliveryStencil;
import com.example.javatspfci.code.util.FileUtil;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryStencilImpl implements DeliveryStencil {

    @Resource
    private DeliveryService deliveryService;

    @Resource
    private OrderService orderService;

    private String CAR_CODE_MATCH = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
    /**
     * 查询
     * @param page 页数
     * @param count 查询数量
     * @param path url路径
     * @return
     */
    @Override
    public Result getAllDeliveryByPage(Integer page, Integer count, String path) {
        Integer totalCount = deliveryService.queryAllDeliverCount();
        Integer totalPage = PageBean.getTotalPage(count,totalCount);
        Integer start =(page - 1) * count;
        List<Delivery> data = deliveryService.listAllDeliverByPage(start, count);
        PageBean<Delivery> deliveryPages = new PageBean<>(totalCount, page, totalPage, count, data);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliveryPages);
        return new Result().result200(message,path);
    }

    /**
     * 通过工厂ID获取配送员
     * @param factoryId 工厂ID
     * @param page 页数
     * @param count 查询数据数量
     * @param path url路径
     * @return
     */
    @Override
    public Result listDeliveryByFactory(String factoryId, Integer page, Integer count, String path) {
        Integer totalCount = deliveryService.queryDeliveryCountByFactoryId(factoryId);
        Integer totalPage = PageBean.getTotalPage(count,totalCount);
        Integer start =(page - 1) * count;
        List<Delivery> data = deliveryService.listDeliveryByFactoryId(factoryId, start, count);
        PageBean<Delivery> deliveryPages = new PageBean<>(totalCount, page, totalPage, count, data);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliveryPages);
        return new Result().result200(message,path);
    }


    /**
     *
     * @param delID 配送员ID
     * @param path url路径
     * @return
     */
    @Override
    public Result getOneDeliveryByID(String delID, String path) {
        Delivery deliverQueryMsg = deliveryService.getOneDeliveryByID(delID);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliverQueryMsg);
        return new Result().result200(message, path);
    }

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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updateDeliveryInfo(String delID, String username, MultipartFile headPicture, MultipartFile drivingLicence,
                                     MultipartFile carLicence, String carCode, String path) throws IOException {
        int updateCode = 1;
        Map<String, Object> data = null;
        //本地设置图片路径
//        String imagePath = "C:/Users/Lenovo/Desktop/image/" + delID;
        //服务器路径
        String imagePath = "/usr/local/src/spring-boot/image/" + delID;
        String headPicturePath = FileUtil.addImg(headPicture,imagePath);
        String drivingLicencePath = FileUtil.addImg(drivingLicence,imagePath);
        String carLicencePath = FileUtil.addImg(carLicence,imagePath);
        boolean updateJudge = false;
        if (!carCode.matches(CAR_CODE_MATCH)){
            updateCode = 0;
            data.put("car_code", "车牌号有误");
        }
        if (updateCode == 1){
            updateJudge = deliveryService.updateDelivery(delID,username,headPicturePath,drivingLicencePath,carLicencePath,carCode);
        }
        if (updateJudge){
            data = new HashMap<>();
            data.put("user_name",username);
            data.put("head_picture",headPicturePath);
            data.put("driving_licence",drivingLicencePath);
            data.put("car_licence",carLicencePath);
            data.put("car_code",carCode);
        } else {
            updateCode = 0;
        }
        Map<String,Object> message = new HashMap<>();
        message.put("data",data);
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @param path url路径
     * @return
     */
    @Override
    public Result queryFindDelivery(String username, String path) {
        Map<String,Object> message = new HashMap<>();
        List<Delivery> data = deliveryService.queryFindDelivery(username);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 删除配送员
     * @param deliveryId 配送员Id
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteDelivery(String deliveryId, String path) {
        int deleteCode = 1;
        Boolean deleteJudge = false;
        deleteJudge = deliveryService.deleteDelivery(deliveryId);
        orderService.orderCancelByDelivery(deliveryId);
        if (!deleteJudge){
            deleteCode = 0;
        }
        Map<String,Object> message = new HashMap<>();
        message.put("delete_code",deleteCode);
        return new Result().result200(message, path);
    }

}
