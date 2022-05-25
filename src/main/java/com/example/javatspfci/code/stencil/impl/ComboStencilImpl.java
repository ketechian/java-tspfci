package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.dto.SimpTableInfoDto;
import com.example.javatspfci.code.entity.po.Combo;
import com.example.javatspfci.code.entity.po.Default;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.ComboService;
import com.example.javatspfci.code.service.DefaultService;
import com.example.javatspfci.code.stencil.ComboStencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: Katechian
 * @CreateTime: 2022/5/17 22:38
 */

@Service
public class ComboStencilImpl implements ComboStencil {

    @Autowired
    private DefaultService defaultService;

    @Resource
    private ComboService comboService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addCombo(String factoryId, String picture, BigDecimal price, String name, String info, int stock, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            defaultService.addCombo(factoryId, picture, price, name, info, stock);
            message.put("data", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "添加失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateCombo(int id, String factoryId, String picture, BigDecimal price, String name, String info, int stock, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            defaultService.updateCombo(id, factoryId, picture, price, name, info, stock);
            message.put("data", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "修改失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result selectComboById(int id, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Default combo = defaultService.selectComboById(id);
            message.put("data", combo);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result countCombo(String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Integer count = defaultService.countCombo();
            message.put("data", count);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result countComboByFactoryId(String factoryId, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            Integer count = defaultService.countComboByFactoryId(factoryId);
            message.put("data", count);
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "查询失败");
        }
        return new Result().result200(message, path);
    }

    @Override
    public Result listAllComboByPage(Integer page, Integer count, String path) {
        Map<String,Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = defaultService.countCombo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Default> data = null;
            try {
                data = defaultService.listAllComboByPage(start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Default> comboPages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", comboPages);
            return new Result().result200(message, path);
        }
    }

    @Override
    public Result listComboByFactory(String factoryId, Integer page, Integer count, String path) {
        Map<String,Object> message = new HashMap<>();
        if (page < 1 || count <= 0) {
            message.put("data", "参数错误");
            return new Result().result200(message, path);
        } else {
            //获取总记录数
            Integer totalCount = null;
            try {
                totalCount = defaultService.countComboByFactoryId(factoryId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取总页数
            Integer totalPage = PageBean.getTotalPage(count, totalCount);
            //获取偏移量
            Integer start = (page - 1) * count;
            //获取数据
            List<Default> data = null;
            try {
                data = defaultService.listComboByFactory(factoryId, start, count);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //封装到PageBean
            PageBean<Default> comboPages = new PageBean<>(totalCount, page, totalPage, count, data);
            message.put("data", comboPages);
            return new Result().result200(message, path);
        }
    }

    @Override
    public Result deleteCombo(Integer id, String path) {
        Map<String, Object> message = new HashMap<>();
        try {
            defaultService.deleteCombo(id);
            message.put("data", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            message.put("data", "删除失败");
        }
        return new Result().result200(message, path);
    }

    /**
     * 添加组合
     * @param factoryId 厂家ID
     * @param storeId 店家ID
     * @param price 价格
     * @param name 姓名
     * @param info 配套信息
     * @param path url路径
     * @return
     */
    @Override
    public Result addStoreCombo(String factoryId, String storeId, Double price, String name, List<SimpTableInfoDto> info, String path) {
        int addCode = 1;
        StringBuffer sb = new StringBuffer("{");
        for (SimpTableInfoDto t:info) {
            sb.append(t.getName() + ",");
        }

        sb.append("} " + price + "元");
        BigDecimal bigPrice = new BigDecimal(price);
        boolean addJudge = comboService.addStoreCombo(factoryId,storeId,bigPrice,name,sb.toString());
        if (!addJudge){
            addCode = 0;
        }
        Map<String, Object> message = new HashMap<>();
        message.put("add_code",addCode);
        return new Result().result200(message, path);
    }

    /**
     * 查看组合
     * @param storeId 店家ID
     * @param factoryId 厂家ID
     * @param path url路径
     * @return
     */
    @Override
    public Result listComboByStore(String storeId, String factoryId, String path) {
        List<Combo> data = comboService.listComboByStore(storeId, factoryId);
        Map<String, Object> message = new HashMap<>();
        message.put("data",data);
        return new Result().result200(message, path);
    }

    /**
     * 删除组合
     * @param comboId 配套ID
     * @param path 厂家
     * @return
     */
    @Override
    public Result deleteStoreCombo(Integer comboId, String path) {
        int deleteCode = 1;
        if (!comboService.deleteStoreCombo(comboId)){
            deleteCode = 0;
        }
        Map<String, Object> message = new HashMap<>();
        message.put("delete_code", deleteCode);
        return new Result().result200(message, path);
    }
}
