package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Delivery;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.DeliveryService;
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
                                     MultipartFile carLicence, MultipartFile carCode, String path) throws IOException {
        int updateCode = 1;
        Map<String, Object> data = null;
        //本地设置图片路径
//        String imagePath = "C:/Users/Lenovo/Desktop/image/" + delID;
        //服务器路径
        String imagePath = "/usr/local/src/spring-boot/image/" + delID;
        String headPicturePath = FileUtil.addImg(headPicture,imagePath);
        String drivingLicencePath = FileUtil.addImg(drivingLicence,imagePath);
        String carLicencePath = FileUtil.addImg(carLicence,imagePath);
        String carCodePath = FileUtil.addImg(carCode,imagePath);
        boolean updateJudge = false;
        if (delID.equals(deliveryService.queryCountByName(username))){
            updateJudge = deliveryService.updateDelivery(delID,username,headPicturePath,drivingLicencePath,carLicencePath,carCodePath);

        }
        if (updateJudge){
            data = new HashMap<>();
            data.put("username",username);
            data.put("head_picture",headPicturePath);
            data.put("driving_licence",drivingLicencePath);
            data.put("car_licence",carLicencePath);
            data.put("car_code",carCodePath);
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

}
