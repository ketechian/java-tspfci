package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Factory;
import com.example.javatspfci.code.entity.vo.FactoryQueryMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.FactoryService;
import com.example.javatspfci.code.stencil.FactoryStencil;
import com.example.javatspfci.code.util.FileUtil;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FactoryStencilImpl implements FactoryStencil {

    @Resource
    private FactoryService factoryService;

    /**
     * 获取全部配送员
     * @param page 页数
     * @param count 查询数量
     * @param path url路径
     * @return
     */
    @Override
    public Result getAllFactoryByPage(Integer page, Integer count, String path) {
        Integer totalCount = factoryService.queryAllFactoryCount();
        Integer totalPage = PageBean.getTotalPage(count,totalCount);
        Integer start =(page - 1) * count;
        List<Factory> data = factoryService.listAllFactoryByPage(start, count);
        PageBean<Factory> factoryPages = new PageBean(totalCount, page, totalPage, count, data);
        Map<String,Object> message = new HashMap<>();
        message.put("data",factoryPages);
        return new Result().result200(message,path);
    }

    /**
     * 通过id获取单个配送员信息
     * @param delID 配送员ID
     * @param path url路径
     * @return
     */
    @Override
    public Result getOneFactoryByID(String delID, String path) {
        FactoryQueryMsg factoryQueryMsgs = factoryService.getOneFactoryByID(delID);
        Map<String,Object> message = new HashMap<>();
        message.put("data",factoryQueryMsgs);
        return new Result().result200(message,path);
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
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateFactory(String id, String username, String factoryName, String introduce,
                                MultipartFile headPicture, String location, MultipartFile factoryLicence, String path) throws IOException {
        int updateCode = 1;
        Map<String, Object> data = new HashMap<>();
        //本地设置图片路径
        //String imagePath = "C:/Users/Lenovo/Desktop/image/" + id;
        //服务器路径
        String imagePath = "/usr/local/src/spring-boot/image/" + id;
        String headPicturePath = FileUtil.addImg(headPicture,imagePath);
        String factoryLicencePath = FileUtil.addImg(factoryLicence,imagePath);
        Boolean updateJude = false;
        updateJude = factoryService.updateFactory(id, username, factoryName, introduce,
                headPicturePath, location, factoryLicencePath);
        if (updateJude){
            data.put("username",username);
            data.put("factory_name",factoryName);
            data.put("introduce",introduce);
            data.put("head_picture",headPicturePath);
            data.put("location",location);
            data.put("factory_licence",factoryLicencePath);
        } else {
            updateCode = 0;
        }
        Map<String,Object> message = new HashMap<>();
        message.put("data",data);
        message.put("update_code",updateCode);
        return new Result().result200(message, path);
    }
}
