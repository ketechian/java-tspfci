package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.*;
import com.example.javatspfci.code.stencil.PasswordStencil;
import com.example.javatspfci.code.util.SecretUtil;
import com.example.javatspfci.code.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * 通用Service接口实现类
 */
@Service
public class PasswordStencilImpl implements PasswordStencil {

    @Resource
    private AllPasswordService allPasswordService;

    @Resource
    private DeliveryService deliveryService;

    @Resource
    private FactoryService factoryService;

    @Resource
    private StoreService storeService;

    @Resource
    private LogService logService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private AdminService adminService;

    private final String PHONE_PATTERN = "^[1][3,4,5,7,8,9]\\d{9}$";

    /**
     * 用户注册
     *
     * @param name       姓名
     * @param phone      电话号码
     * @param role       身份
     * @param password   密码
     * @param rwPassword 确认密码
     * @param logStatus  操作状态
     * @param path       url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result userRegister(String name, String phone, String role, String password, String rwPassword, String logStatus, String path) {
        Map<String, Object> data = new HashMap<>();
        //初始默认返回码
        Integer registerCode = 1;
        //判断用户名是否重复

        //判断手机号码是否符合格式
        Boolean phoneJudge = Pattern.matches(PHONE_PATTERN, phone);
        if (phoneJudge) {
            data.put("phone", "手机格式正确");
        } else {
            data.put("phone", "手机格式不正确");
            registerCode = 0;
        }
        //判断密码是否大于六位小于20位
        Boolean passwordJudge = passwordLen(password);
        if (passwordJudge) {
            data.put("password", "密码符合要求");
        } else {
            data.put("password", "请输入大于6位小于20位的密码");
            registerCode = 0;
        }
        //判断密码输入是否前后一致
        Boolean rwPasswordJudge = password.equals(rwPassword);
        if (rwPasswordJudge) {
            data.put("rw_password", "前后密码一致");
        } else {
            data.put("rw_password", "前后密码不一致");
            registerCode = 0;
        }
        //判断权限是否是对应的前端权限输入
        Boolean roleJudge = role.equals("user") || role.equals("userAdmin");
        if (roleJudge) {
            data.put("role", "权限正确");
        } else {
            data.put("role", "权限不正确");
            registerCode = 0;
        }
        if (registerCode == 1) {
            String userId = UUIDUtil.getUUID();
            String secretPassword = SecretUtil.secretString(password);
            switch (role) {
                case "user":
                    if (!storeService.queryCountByPhone(phone)) {
                        allPasswordService.addUser(userId, secretPassword);
                        storeService.addStore(userId, name, phone);
                    } else {
                        data.put("name", "用户名不可用");
                        registerCode = 0;
                    }
                    break;
                case "userAdmin":
                    if (!factoryService.queryCountByPhone(phone)) {
                        allPasswordService.addUser(userId, secretPassword);
                        factoryService.addFactory(userId, name, phone);
                    } else {
                        data.put("name", "用户名不可用");
                        registerCode = 0;
                    }
                    break;
            }
            if (registerCode == 1) {
                userRoleService.addRole(userId, role);
                logService.addLog(userId, logStatus);
            }

        }
        Map<String, Object> message = new HashMap<>();
        message.put("register_code", registerCode);
        message.put("data", data);
        return new Result().result200(message, path);
    }

    /**
     /**
     * 配送员注册
     * @param name 姓名
     * @param phone 电话号码
     * @param factoryId 配送员厂家ID
     * @param role 权限名
     * @param logStatus 操作状态
     * @param path url路径
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deliveryRegister(String name, String phone, String factoryId,String role, String logStatus, String path) {
        Map<String, Object> data = new HashMap<>();
        //初始默认返回码
        Integer registerCode = 1;
        //判断手机号码是否符合格式
        Boolean phoneJudge = Pattern.matches(PHONE_PATTERN, phone);
        if (phoneJudge) {
            data.put("phone", "手机格式正确");
        } else {
            data.put("phone", "手机格式不正确");
            registerCode = 0;
        }
        String delID = UUIDUtil.getUUID();
        String password = createRandomPassword(phone);
        if (registerCode == 1){
            if (!deliveryService.queryCountByPhone(phone)){
                String secretPassword = SecretUtil.secretString(password);
                allPasswordService.addUser(delID, secretPassword);
                userRoleService.addRole(delID, role);
                deliveryService.addDelivery(delID, name, phone, factoryId);
            } else {
                data.put("name", "用户名不可用");
                registerCode = 0;
            }

        }
        Map<String, Object> message = new HashMap<>();
        message.put("register_code",registerCode);
        message.put("data",data);
        message.put("password",password);
        return new Result().result200(message, path);
    }

    /**
     * 管理员注册
     *
     * @param adminName  管理员用户名
     * @param password   密码
     * @param rwPassword 确认密码
     * @param role       角色权限
     * @param logStatus  操作状态
     * @param path       url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result adminRegister(String adminName, String password, String rwPassword, String role, String logStatus, String path) throws IOException {
        Map<String, Object> data = new HashMap<>();
        Integer registerCode = 1;
        //判断用户名是否已用
        Boolean nameJudge = adminService.queryAdminCountByUsername(adminName) != null;
        if (!nameJudge) {
            data.put("username", "用户名可用");
        } else {
            data.put("username", "用户名已使用");
            registerCode = 0;
        }
        //判断密码是否大于6位小于20位
        Boolean passwordJudge = passwordLen(password);
        if (passwordJudge) {
            data.put("password", "密码符合要求");
        } else {
            data.put("password", "请输入大于6位小于20位的密码");
            registerCode = 0;
        }
        //判断前后密码是否一致
        Boolean rwPasswordJudge = password.equals(rwPassword);
        if (rwPasswordJudge) {
            data.put("rw_password", "前后密码一致");
        } else {
            data.put("rw_password", "前后密码不一致");
            registerCode = 0;
        }
        Boolean roleJudge = role.equals("superAdmin");
        //判断是否为管理员权限注册
        if (roleJudge) {
            data.put("role", "权限正确");
        } else {
            data.put("role", "权限不正确");
            registerCode = 0;
        }
        if (registerCode == 1) {
            String adminId = UUIDUtil.getUUID();
            String secretPassword = SecretUtil.secretString(password);
            allPasswordService.addUser(adminId, secretPassword);
            adminService.addAdmin(adminId, adminName);
            userRoleService.addRole(adminId, role);
            logService.addLog(adminId, logStatus);
        }
        Map<String, Object> message = new HashMap<>();
        message.put("register_code", registerCode);
        message.put("data", data);
        return new Result().result200(message, path);
    }

    /**
     * 修改密码
     * @param id 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param rwPassword 确认密码
     * @param path url路径
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updatePassword(String id, String oldPassword, String newPassword, String rwPassword, String path) {
        int updatePasswordCode = 1;
        Map<String,Object> data = new HashMap<>();
        String secretOldPassword = SecretUtil.secretString(oldPassword);
        if (allPasswordService.findCountByPassword(id,secretOldPassword)){
            data.put("old_password","密码正确");
        } else {
            updatePasswordCode = 0;
            data.put("old_password","密码错误");
        }
        if (passwordLen(newPassword)){
            data.put("new_password","密码格式正确");
        } else {
            data.put("new_password","密码格式错误");
            updatePasswordCode = 0;
        }
        if (newPassword.equals(rwPassword)){
            data.put("rw_password","两次密码一致");
        } else {
            data.put("rw_password","两次密码不一致");
            updatePasswordCode = 0;
        }
        if (updatePasswordCode == 1){
            String secretNewPassword = SecretUtil.secretString(newPassword);
            allPasswordService.updatePassword(id, secretNewPassword);
        }
        Map<String,Object> message = new HashMap<>();
        message.put("data", data);
        message.put("update_password_code", updatePasswordCode);
        return new Result().result200(message, path);
    }

    /**
     * 判断密码是否符合要求
     *
     * @param password 密码
     * @return
     */
    private Boolean passwordLen(String password) {
        int length = password.length();
        return (length >= 6) && (length <= 20);
    }

    /**
     * 随机生成一个六位数密码
     * @return
     */
    private String createRandomPassword(String phone){
        String password = "tspfci" + phone.substring(7,11);
        return password;
    }
}
