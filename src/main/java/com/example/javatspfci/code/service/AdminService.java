package com.example.javatspfci.code.service;

import com.example.javatspfci.code.entity.po.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javatspfci.code.entity.vo.AdminLoginMsg;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
public interface AdminService extends IService<Admin> {

    /**
     * 添加管理员
     * @param id 管理员id
     * @param username 管理员姓名
     * @return
     */
    public Boolean addAdmin(String id, String username);

    /**
     * 查询管理员用户名是否重复
     * @param username 管理员用户名
     * @return
     */
    public String queryAdminCountByUsername(String username);

    /**
     * 管理员登录
     * @param username 管理员用户名
     * @param password 密码
     * @return
     */
    public AdminLoginMsg AdminLogin(String username, String password);

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    public AdminLoginMsg queryAdminById(String id);

    /**
     * 修改管理员信息
     * @param id 管理员ID
     * @param username 用户名
     * @param headPicture 用户头像图片
     * @return
     */
    public Boolean updateAdminInfo(String id, String username, String headPicture);
}
