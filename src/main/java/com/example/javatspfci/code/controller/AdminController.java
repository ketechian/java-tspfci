package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.AdminLoginDto;
import com.example.javatspfci.code.entity.vo.AdminLoginMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.AdminService;
import com.example.javatspfci.code.stencil.AdminStencil;
import com.example.javatspfci.code.stencil.LoginStencil;
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
@RequestMapping("/code/admin")
public class AdminController {
    @Autowired
    private LoginStencil loginStencil;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminStencil adminStencil;

    @PostMapping("/login")
    public Result adminLogin(@RequestBody AdminLoginDto adminLoginDto, @RequestHeader(value = "token",required = false) String token) {
        //md5加密
        String md5Password = SecretUtil.secretString(adminLoginDto.getPassword());
        //查询用户是否存在
        AdminLoginMsg adminMsg = null;
        try {
             adminMsg = adminService.AdminLogin(adminLoginDto.getAdminUserName(), md5Password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取Result
        Result result = loginStencil.adminLogin(adminMsg, "login", "/code/admin/login", token);
        return result;
    }



    /**
     * 根据id获取管理员信息
     * @param id 管理员id
     * @return
     */
    @GetMapping("/getAdminInformation")
    public Result getAdminInformation(@RequestParam String id){
        Result result = adminStencil.getOneAdminById(id, "/code/admin/getAdminInformation");
        return result;
    }

    @PostMapping("/updateAdminInfo")
    public Result updateAdminInfo(@RequestParam String id,
                                  @RequestParam String username,
                                  @RequestParam MultipartFile headPicture) throws IOException {
        return adminStencil.updateAdminInfo(id, username, headPicture, "/code/admin/updateAdminInfo");
    }

//    @GetMapping("/queryInfoCount")
//    public Result queryInfoCount(){
//        return adminStencil.queryAllTableCount("/code/admin/queryInfoCount");
//    }
}
