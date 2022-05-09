package com.example.javatspfci.code.stencil.impl;

import com.example.javatspfci.code.entity.bean.PageBean;
import com.example.javatspfci.code.entity.po.Delivery;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.service.DeliveryService;
import com.example.javatspfci.code.stencil.DeliveryStencil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryStencilImpl implements DeliveryStencil {

    @Resource
    private DeliveryService deliveryService;

    /**
     * 查询
     * @param page 页数
     * @param count 查询数量
     * @param path url路径
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result getAllDeliveryByPage(Integer page, Integer count, String path) {
        Integer totalCount = deliveryService.queryAllDeliverCount();
        Integer totalPage = PageBean.getTotalPage(count,totalCount);
        Integer start =(page - 1) * count;
        List<Delivery> data = deliveryService.listAllDeliverByPage(start, count);
        PageBean<Delivery> deliveryPages = new PageBean<>(totalCount, page, totalPage, count, data);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliveryPages);
        return new Result().result200(message,path);
    }

    /**
     * 通过工厂ID获取配送员
     * @param factoryId 工厂ID
     * @param page 页数
     * @param count 查询数据数量
     * @param path url路径
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result listDeliveryByFactory(String factoryId, Integer page, Integer count, String path) {
        Integer totalCount = deliveryService.queryDeliveryCountByFactoryId(factoryId);
        Integer totalPage = PageBean.getTotalPage(count,totalCount);
        Integer start =(page - 1) * count;
        List<Delivery> data = deliveryService.listDeliveryByFactoryId(factoryId, start, count);
        PageBean<Delivery> deliveryPages = new PageBean<>(totalCount, page, totalPage, count, data);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliveryPages);
        return new Result().result200(message,path);
    }


    /**
     *
     * @param delID 配送员ID
     * @param path url路径
     * @return
     */
    @Override
    public Result getOneDeliveryByID(String delID, String path) {
        Delivery deliverQueryMsg = deliveryService.getOneDeliveryByID(delID);
        Map<String,Object> message = new HashMap<>();
        message.put("data",deliverQueryMsg);
        return new Result().result200(message, path);
    }

}
