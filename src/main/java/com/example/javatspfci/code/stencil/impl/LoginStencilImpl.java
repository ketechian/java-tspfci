package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.vo.AdminLoginMsg;
import com.example.javatspfci.code.entity.vo.DeliveryLoginMsg;
import com.example.javatspfci.code.entity.vo.FactoryLoginMsg;
import com.example.javatspfci.code.entity.vo.StoreLoginMsg;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.LogService;
import com.example.javatspfci.code.stencil.LoginStencil;
import com.example.javatspfci.code.util.JWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Hashtable;
import java.util.Map;

/**
 * @Description
 * @Author: HZY
 * @CreateTime: 2022/5/2 00:16
 */
@Service
public class LoginStencilImpl implements LoginStencil {
    @Resource
    private LogService logService;

    /**
     * 用户(店家)登录
     * @param storeMsg  店家信息
     * @param logStatus 操作状态
     * @param path      url路径
     * @param token     token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result storeLogin(StoreLoginMsg storeMsg, String logStatus, String path, String token) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        if (storeMsg != null) {
            tokenMap.put("ID", storeMsg.getStId());
            tokenMap.put("role", storeMsg.getURole());
            Result result = new LoginStencilImpl().loginJudge(storeMsg, resultMap, tokenMap, path, token);
            if ((int)resultMap.get("login_code") == 1) {
                //在记录表中插入数据
                logService.addLog(tokenMap.get("ID"), logStatus);
            }
            return result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    /**
     * 管理员登录
     * @param adminMsg 管理员信息
     * @param path url路径
     * @param logStatus 操作状态
     * @param token token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result adminLogin(AdminLoginMsg adminMsg, String logStatus, String path, String token) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        if (adminMsg != null) {
            tokenMap.put("ID", adminMsg.getAdId());
            tokenMap.put("role", adminMsg.getURole());
            Result result = new LoginStencilImpl().loginJudge(adminMsg, resultMap, tokenMap, path, token);
            if ((int)resultMap.get("login_code") == 1) {
                //在记录表中插入数据
                logService.addLog(tokenMap.get("ID"), logStatus);
            }
            return result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    /**
     * 厂家登录
     * @param factoryMsg 厂家用户名
     * @param logStatus  操作状态
     * @param path       请求路径
     * @param token      token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result factoryLogin(FactoryLoginMsg factoryMsg, String logStatus, String path, String token) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        if (factoryMsg != null) {
            tokenMap.put("ID", factoryMsg.getFacId());
            tokenMap.put("role", factoryMsg.getURole());
            Result result = new LoginStencilImpl().loginJudge(factoryMsg, resultMap, tokenMap, path, token);
            if ((int)resultMap.get("login_code") == 1) {
                //在记录表中插入数据
                logService.addLog(tokenMap.get("ID"), logStatus);
            }
            return result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    /**
     * @param deliveryMsg 配送员信息
     * @param logStatus   操作状态
     * @param path        请求路径
     * @param token       token
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deliveryLogin(DeliveryLoginMsg deliveryMsg, String logStatus, String path, String token) {
        Map<String, Object> resultMap = new Hashtable<>();
        Map<String, String> tokenMap = new Hashtable<>();

        if (deliveryMsg != null) {
            tokenMap.put("ID", deliveryMsg.getDeId());
            tokenMap.put("role", deliveryMsg.getURole());
            Result result = new LoginStencilImpl().loginJudge(deliveryMsg, resultMap, tokenMap, path, token);
            if ((int)resultMap.get("login_code") == 1) {
                //在记录表中插入数据
                logService.addLog(tokenMap.get("ID"), logStatus);
            }
            return result;
        } else {
            //账号或密码错误
            resultMap.put("login_code", 0);
            resultMap.put("data", "账号或密码错误");
            return new Result().result200(resultMap, path);
        }
    }

    /**
     * @param msg       传入对应的vo
     * @param resultMap result的数据
     * @param tokenMap  token中的值
     * @param path      请求路径
     * @param token     token
     * @return
     */
    public Result loginJudge(Object msg, Map<String, Object> resultMap, Map<String, String> tokenMap,
                             String path, String token) {
        if ("".equals(token) || token == null) {
            //登陆成功,生成token
            token = JWTUtil.createToken(tokenMap);
            //返回
            resultMap.put("login_code", 1);
            resultMap.put("token", token);
            resultMap.put("data", msg);
            return new Result().result200(resultMap, path);
        } else {
            //不是新登录,验证token
            int statusCode = JWTUtil.verify(token);
            if (statusCode == 1) {
                //token未过期,登录成功
                //刷新token
                token = JWTUtil.createToken(tokenMap);
                //返回
                resultMap.put("login_code", 1);
                resultMap.put("token", token);
                resultMap.put("data", msg);
                return new Result().result200(resultMap, path);
            } else {
                //token失效或错误
                resultMap.put("login_code", 0);
                //清空token
                token = "";
                resultMap.put("token", token);
                return new Result().result401(resultMap, path);
            }
        }
    }
}
