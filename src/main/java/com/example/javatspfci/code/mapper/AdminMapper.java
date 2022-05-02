package com.example.javatspfci.code.mapper;

import com.example.javatspfci.code.entity.po.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javatspfci.code.entity.vo.AdminMsg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-29
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 管理员注册
     * @param id 管理员id
     * @param username 管理员姓名
     * @return
     */
    public Integer addAdmin(String id, String username);

    /**
     * 查询管理员用户名是否重复
     * @param username 管理员用户名
     * @return
     */
    public Integer queryAdminCountByUsername(String username);

    /**
     * 管理员登录
     * @param username 管理员用户名
     * @param password 密码
     * @return
     */
    public AdminMsg selectAdminUserByUsername(String username, String password);

}
