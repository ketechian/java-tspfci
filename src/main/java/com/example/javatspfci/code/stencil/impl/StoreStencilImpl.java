package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Delivery;
import com.example.javatspfci.code.entity.po.Store;
import com.example.javatspfci.code.entity.vo.StoreLoginMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.OrderService;
import com.example.javatspfci.code.service.StoreService;
import com.example.javatspfci.code.stencil.StoreStencil;
import com.example.javatspfci.code.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/4 20:38
 */
@Service
public class StoreStencilImpl implements StoreStencil {

    @Autowired
    private StoreService storeService;

    @Resource
    private OrderService orderService;

    @Override
    public Result getOneStoreById(String id, String path) {
        StoreLoginMsg storeMsg = null;
        Map<String,Object> message = new HashMap<>();

        try {
             storeMsg = storeService.queryStoreById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (storeMsg == null){
            message.put("data", "查无此人");
        } else {
            message.put("data", storeMsg);
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result getAllStoreByPage(String factoryId, Integer page, Integer count, String path) {
        Map<String,Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = storeService.queryAllStoreCount();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Store> data = null;
            try {
                data = storeService.listAllStoreByPage(factoryId, start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Store> storePages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", storePages);
            return new Result().result200(message, path);
        }
    }

    /**
     * 更新店家信息
     * @param id 店家ID
     * @param ownerName 店主姓名
     * @param storeName 店名
     * @param introduce 店家介绍
     * @param headPicture 店面图片
     * @param location 店家地址
     * @param licence 经营许可证图片
     * @param path url路径
     * @return
     */
    @Override
    public Result updateStoreInfo(String id, String username, String ownerName, String storeName, String introduce,
                                  MultipartFile headPicture, String location, MultipartFile licence, String path) throws IOException {
        int updateCode = 1;
        Map<String, Object> data = null;
        //本地设置图片路径
//        String imagePath = "C:/Users/Lenovo/Desktop/image/" + id;
        //服务器路径
        String imagePath = "/usr/local/src/spring-boot/image/" + id;
        String hedadPicturePath = FileUtil.addImg(headPicture, imagePath);
        String licencePath = FileUtil.addImg(licence, imagePath);
        Boolean updateJudge = false;
        updateJudge = storeService.updateStoreInfo(id, username, ownerName, storeName, introduce,
                    hedadPicturePath, location, licencePath);
        if (updateJudge){
            data = new HashMap<>();
            data.put("username",username);
            data.put("ownerName",ownerName);
            data.put("introduce",introduce);
            data.put("headPicture",hedadPicturePath);
            data.put("location",location);
            data.put("licence",licencePath);
        }
        Map<String, Object> message = new HashMap<>();
        message.put("update_code",updateCode);
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 商家添加合作厂家
     * @param storeId 商家ID
     * @param factoryId 厂家ID
     * @return
     */
    @Override
    public Result addCooperation(String storeId, String factoryId, String path) {
        int updateCode = 1;
        Boolean updateJudge = storeService.addCooperation(storeId, factoryId);
        if (!updateJudge){
            updateCode = 0;
        }
        Map<String, Object> message = new HashMap<>();
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }

    /**
     * 根据厂家Id获取用户信息
     * @param factoryId 厂家Id
     * @param path url路径
     * @return
     */
    @Override
    public Result listStoreByFactoryId(String factoryId, String path) {
        List<Store> storeList = storeService.listStoreByFactoryId(factoryId);
        Map<String, Object> message = new HashMap<>();
        message.put("data",storeList);
        return new Result().result200(message, path);
    }

    /**
     *
     * @param factoryId 厂家ID
     * @param storeId 店家
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result removeCooperation(String factoryId, String storeId, String path) {
        int updateCode = 1;
        Boolean cancelJudge = storeService.deleteCooperation(factoryId, storeId);
        orderService.orderCancelByFactoryAndStore(factoryId, storeId);
        if (!cancelJudge){
            updateCode = 0;
        }
        Map<String, Object> message =new HashMap<>();
        message.put("update_code", updateCode);
        return new Result().result200(message, path);
    }
}
