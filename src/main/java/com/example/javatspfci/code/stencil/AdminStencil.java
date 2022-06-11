package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.entity.vo.AdminLoginMsg;
import com.example.javatspfci.code.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/4 20:37
 */
public interface AdminStencil {
    /**
     * 根据id查询管理员
     * @param id 管理员id
     * @param path 请求路径
     * @return
     */
    public Result getOneAdminById(String id, String path);

    /**
     * 修改管理员信息
     * @param id 管理员ID
     * @param username 用户名
     * @param headPicture 用户头像图片
     * @param path url路径
     * @return
     */
    public Result updateAdminInfo(String id, String username, MultipartFile headPicture, String path) throws IOException;

}
