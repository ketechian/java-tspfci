package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Tableware;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.TablewareService;
import com.example.javatspfci.code.stencil.TablewareStencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TablewareStencilImpl implements TablewareStencil {

    @Autowired
    private TablewareService tablewareService;

    /**
     * 添加餐具
     *
     * @param factoryId 餐具所属厂家ID
     * @param name      餐具名
     * @param describe  餐具描述
     * @param type      餐具类型
     * @param picture   餐具图片
     * @param price     餐具具价格
     * @param stock     餐具库存
     * @param path      url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addTableware(String factoryId, String name, String describe, String type, String picture, String size,
                               BigDecimal price, int stock, String path) {
        Map<String, Object> message = new HashMap<>();
        message.put("data", "null");
        //根据餐具类型来设置置权值
        int value = 0;
        //筷子
        if ("H".equals(type) | "O".equals(type)) {
            value = 0;
            //盘子
        } else if ("P".equals(type)) {
            value = 18;
            //杯子
        } else if ("C".equals(type)) {
            value = 19;
            //碗
        } else if ("B".equals(type)) {
            value = 20;
            //勺子
        } else if ("S".equals(type)) {
            value = 21;
            //碟子
        } else if ("D".equals(type)) {
            value = 22;
        } else {
            //参数错误
            message.remove("data");
            message.put("data", "参数错误");
        }

        if (message.get("data") != "参数错误") {
            try {
                tablewareService.addTableware(factoryId, name, describe, type, picture, size, price, stock, value);
                message.remove("data");
                message.put("data", "添加成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (message.get("data") == "null") {
            message.put("data", "添加失败");
        }

        return new Result().result200(message, path);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateTableware(int id, String factoryId, String name, String describe, String picture,
                                  String size, BigDecimal price, int stock, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            tablewareService.updateTableware(id, factoryId, name, describe, picture, size, price, stock);
            message.put("data", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "修改失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result selectTablewareById(int id, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Tableware tableware = tablewareService.selectTablewareById(id);
            message.put("data", tableware);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result countTableware(String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Integer countTableware = tablewareService.countTableware();
            message.put("data", countTableware);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result countTablewareByFactoryId(String factoryId, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Integer countTableware = tablewareService.countTablewareByFactoryId(factoryId);
            message.put("data", countTableware);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result listAllTablewareByPage(Integer page, Integer count, String path) {
        Map<String, Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = tablewareService.countTableware();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Tableware> data = null;
            try {
                data = tablewareService.listAllTablewareByPage(start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Tableware> tablewarePages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", tablewarePages);
            return new Result().result200(message, path);
        }
    }

    @Override
    public Result listTablewareByFactory(String factoryId, Integer page, Integer count, String path) {
        Map<String, Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = tablewareService.countTablewareByFactoryId(factoryId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Tableware> data = null;
            try {
                data = tablewareService.listTablewareByFactory(factoryId, start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Tableware> tablewarePages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", tablewarePages);
            return new Result().result200(message, path);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteTableware(Integer id, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            tablewareService.deleteTableware(id);
            message.put("data", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "删除失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result listTablewareByType(String type, Integer page, Integer count, String path) {
        Map<String, Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = tablewareService.countTablewareByType(type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Tableware> data = null;
            try {
                data = tablewareService.listTablewareByType(type, start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Tableware> tablewarePages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", tablewarePages);
            return new Result().result200(message, path);
        }
    }

    @Override
    public Result listTablewareByCondition(String factoryId, String type, Integer page, Integer count, String path) {
        Map<String, Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = tablewareService.countTablewareByCondition(factoryId, type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Tableware> data = null;
            try {
                data = tablewareService.listTablewareByCondition(factoryId, type, start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Tableware> tablewarePages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", tablewarePages);
            return new Result().result200(message, path);
        }
    }
}
