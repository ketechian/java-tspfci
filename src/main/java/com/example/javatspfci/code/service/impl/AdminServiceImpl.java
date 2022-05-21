package com.example.javatspfci.code.service.impl;

import com.example.javatspfci.code.entity.po.Admin;
import com.example.javatspfci.code.entity.vo.AdminLoginMsg;
import com.example.javatspfci.code.mapper.AdminMapper;
import com.example.javatspfci.code.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 添加管理员
     * @param id 管理员id
     * @param username 管理员姓名
     * @return
     */
    @Override
    public Boolean addAdmin(String id, String username) {
        return adminMapper.addAdmin(id, username) == 1;
    }

    /**
     * 查询管理员用户名是否重复
     * @param username 管理员用户名
     * @return
     */
    @Override
    public String queryAdminCountByUsername(String username) {
        return adminMapper.queryAdminCountByUsername(username);
    }

    /**
     * 管理员登录
     * @param username 管理员用户名
     * @param password 密码
     * @return
     */
    @Override
    public AdminLoginMsg AdminLogin(String username, String password) {
        return adminMapper.selectAdminUserByUsername(username, password);
    }

    /**
     * 通过id查询管理员
     * @param id 管理员id
     * @return
     */
    @Override
    public AdminLoginMsg queryAdminById(String id) {
        return adminMapper.selectAdminUserById(id);
    }

    /**
     * 修改管理员信息
     * @param id 管理员ID
     * @param username 用户名
     * @param headPicture 用户头像图片
     * @return
     */
    @Override
    public Boolean updateAdminInfo(String id, String username, String headPicture) {
        return adminMapper.updateAdminInfo(id, username, headPicture) == 1;
    }
}
