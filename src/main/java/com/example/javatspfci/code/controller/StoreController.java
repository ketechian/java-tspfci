package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.CancelCooperationDto;
import com.example.javatspfci.code.entity.dto.StoreAddCooperationDto;
import com.example.javatspfci.code.entity.dto.StoreLoginDto;
import com.example.javatspfci.code.entity.vo.StoreLoginMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.StoreService;
import com.example.javatspfci.code.stencil.LoginStencil;
import com.example.javatspfci.code.stencil.StoreStencil;
import com.example.javatspfci.code.util.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/code/store")
public class StoreController {
    @Autowired
    private LoginStencil loginStencil;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreStencil storeStencil;

    @PostMapping("/login")
    public Result storeLogin(@RequestBody StoreLoginDto storeLoginDto, @RequestHeader(value = "token", required = false) String token) {
        //md5加密
        String md5Password = SecretUtil.secretString(storeLoginDto.getPassword());
        //查询用户是否存在
        StoreLoginMsg storeMsg = null;
        try {
            storeMsg = storeService.storeLogin(storeLoginDto.getStPhone(), md5Password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取Result
        Result result = loginStencil.storeLogin(storeMsg, "login", "/code/store/login", token);

        return result;
    }

    /**
     * 根据id获取店家信息
     * @param id 店家id
     * @return
     */
    @GetMapping("/getStoreInformation")
    public Result getStoreInformation(@RequestParam String id){
        Result result = storeStencil.getOneStoreById(id, "/code/store/getStoreInformation");
        return result;
    }

    /**
     * 分页查询店家
     * @param page 页数
     * @param count 查询数量
     * @return
     */
    @GetMapping("/getAllStoreByPage")
    public Result getAllStoreByPage(@RequestParam String factoryId,
                                    @RequestParam Integer page,
                                    @RequestParam Integer count){
        return storeStencil.getAllStoreByPage(factoryId, page,count,"/code/store/getAllStoreByPage");
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
     * @return
     * @throws IOException
     */
    @PostMapping("/updateStoreInfo")
    public Result updateStoreInfo(@RequestParam String id,
                                  @RequestParam String username,
                                  @RequestParam String ownerName,
                                  @RequestParam String storeName,
                                  @RequestParam String introduce,
                                  @RequestParam MultipartFile headPicture,
                                  @RequestParam String location,
                                  @RequestParam MultipartFile licence) throws IOException {
        return storeStencil.updateStoreInfo(id, username, ownerName, storeName, introduce, headPicture, location, licence,"/code/store/updateStoreInfo");
    }

    /**
     * 添加合作
     * @param storeAddCooperationDto
     * @return
     */
    @PostMapping("/addCooperation")
    public Result addCooperation(@RequestBody StoreAddCooperationDto storeAddCooperationDto){
        return storeStencil.addCooperation(storeAddCooperationDto.getStoreId(),
                storeAddCooperationDto.getFactoryId(),"/code/store/addCooperation");
    }

    /**
     * 根据厂家获取合作店家接口
     * @param factoryId
     * @return
     */
    @GetMapping("/listStoreByFactoryId")
    public Result listStoreByFactoryId(@RequestParam String factoryId){
        return storeStencil.listStoreByFactoryId(factoryId, "/code/store/listStoreByFactoryId");
    }

    /**
     * 取消合作接口
     * @param cancelCooperationDto
     * @return
     */
    @PostMapping("/removeCooperation")
    public Result removeCooperation(@RequestBody CancelCooperationDto cancelCooperationDto){
        return storeStencil.removeCooperation(
                cancelCooperationDto.getFactoryId(),
                cancelCooperationDto.getStoreId(), "/code/store/removeCooperation");
    }
}
