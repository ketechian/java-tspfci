package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.DeLoginDto;
import com.example.javatspfci.code.entity.vo.DeliveryLoginMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.DeliveryService;
import com.example.javatspfci.code.stencil.DeliveryStencil;
import com.example.javatspfci.code.stencil.LoginStencil;
import com.example.javatspfci.code.util.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@RestController
@RequestMapping("/code/delivery")
public class DeliveryController {

    @Autowired
    private LoginStencil loginStencil;

    @Autowired
    private DeliveryService deliveryService;

    @Resource
    private DeliveryStencil deliveryStencil;

    @PostMapping("/login")
    public Result deliveryLogin(@RequestBody DeLoginDto deLoginDto, @RequestHeader(value = "token",required = false) String token) {
        //md5加密
        String md5Password = SecretUtil.secretString(deLoginDto.getPassword());
        //查询用户是否存在
        DeliveryLoginMsg deliveryMsg = null;
        try {
            deliveryMsg = deliveryService.deliveryLogin(deLoginDto.getDeUserName(), md5Password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取Result
        Result result = loginStencil.deliveryLogin(deliveryMsg, "login", "/code/delivery/login", token);
        return result;
    }

    /**
     * 查询
     * @param page 页数
     * @param count 查询数量
     * @return
     */
    @GetMapping("/getAllDeliveryByPage")
    public Result getAllDeliveryByPage(@RequestParam Integer page,
                                       @RequestParam Integer count){
        return deliveryStencil.getAllDeliveryByPage(page,count,"/code/delivery/getAllDeliveryByPage");
    }

    /**
     * 通过id查询配送员
     * @param id 配送员id
     * @return
     */
    @GetMapping("/getOneDeliveryByID")
    public Result getOneDeliveryByID(@RequestParam String id){
        return deliveryStencil.getOneDeliveryByID(id,"/code/delivery/getOneDeliveryByID");
    }
}
