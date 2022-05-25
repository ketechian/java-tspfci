package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.vo.AdminLoginMsg;
import com.example.javatspfci.code.entity.vo.CountMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.*;
import com.example.javatspfci.code.stencil.AdminStencil;
import com.example.javatspfci.code.util.FileUtil;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/4 20:37
 */
@Service
public class AdminStencilImpl implements AdminStencil {

    @Autowired
    private AdminService adminService;

    @Resource
    private StoreService storeService;

    @Resource
    private FactoryService factoryService;

    @Resource
    private DeliveryService deliveryService;

    @Resource
    private OrderService orderService;

    @Override
    public Result getOneAdminById(String id, String path) {
        AdminLoginMsg adminMsg = null;
        Map<String,Object> message = new HashMap<>();

        try {
            adminMsg = adminService.queryAdminById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (adminMsg == null){
            message.put("data", "查无此人");
        } else {
            message.put("data", adminMsg);
        }
        return new Result().result200(message, path);
    }

    /**
     * 修改管理员信息
     * @param id 管理员ID
     * @param username 用户名
     * @param headPicture 用户头像图片
     * @param path url路径
     * @return
     */
    @Override
    public Result updateAdminInfo(String id, String username, MultipartFile headPicture, String path) throws IOException {
        int updateCode = 1;
        Map<String, Object> data = null;
        //本地设置图片路径
//        String imagePath = "C:/Users/Lenovo/Desktop/image/" + id;
        //服务器路径
        String imagePath = "/usr/local/src/spring-boot/image/" + id;
        String headPicturePath = FileUtil.addImg(headPicture, imagePath);
        Boolean updateJudge = false;
        String queryId = adminService.queryAdminCountByUsername(username);
        if (id.equals(queryId)){
            updateJudge = adminService.updateAdminInfo(id, username, headPicturePath);
        } else {
            data = new HashMap<>();
            data.put("username","用户名已使用");
            updateCode = 0;
        }
        if (updateJudge){
            data = new HashMap<>();
            data.put("username",username);
            data.put("headPicture",headPicturePath);
        }
        Map<String, Object> message = new HashMap<>();
        message.put("update_code",updateCode);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 查看各用户数量
     * @param path url路径
     * @return
     */
    public Result queryAllTableCount(String path){
        Integer factoryCount = factoryService.queryAllFactoryCount();
        Integer deliveryCount = deliveryService.queryAllDeliverCount();
        Integer storeCount = storeService.queryAllStoreCount();
        Integer orderCount = orderService.queryAllOrderCount();
        Map<String, Object> message = new HashMap<>();
        message.put("data",null);
        return new Result().result200(message, path);
    }
}
