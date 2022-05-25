package com.example.javatspfci.code.stencil;

import com.example.javatspfci.code.entity.dto.SimpTableInfoDto;
import com.example.javatspfci.code.entity.po.Default;
import com.example.javatspfci.code.result.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description
 * @Author: Katechian
 * @CreateTime: 2022/5/17 22:34
 */

@Service
public interface ComboStencil {
    /**
     * 添加套餐
     * @param factoryId 对应厂家id
     * @param picture 图片路径
     * @param price 总价格
     * @param name 套餐名
     * @param info 套餐内容
     * @param stock 库存数
     * @param path url
     * @return
     */
    public Result addCombo(String factoryId, String picture, BigDecimal price, String name, String info, int stock, String path);

    /**
     * 修改套餐
     * @param id id
     * @param factoryId 对应厂家id
     * @param picture 图片路径
     * @param price 总价格
     * @param name 套餐名
     * @param info 套餐内容
     * @param stock 库存数
     * @param path url
     * @return
     */
    public Result updateCombo(int id, String factoryId, String picture, BigDecimal price, String name, String info, int stock, String path);

    /**
     * 根据id查询套餐
     * @param id id
     * @param path url
     * @return
     */
    public Result selectComboById(int id, String path);

    /**
     * 查询套餐种类数
     * @param path url
     * @return
     */
    public Result countCombo(String path);

    /**
     * 查询指定厂家套餐种类数
     * @param factoryId 厂家id
     * @return
     */
    public Result countComboByFactoryId(String factoryId, String path);

    /**
     * 分页查询套餐信息
     * @param page 开始位置
     * @param count 查多少个
     * @param path url
     * @return
     */
    public Result listAllComboByPage(Integer page, Integer count, String path);

    /**
     * 根据厂家分页查询套餐信息
     * @param factoryId 厂家id
     * @param page 开始位置
     * @param count 查多少个
     * @param path url
     * @return
     */
    public Result listComboByFactory(String factoryId, Integer page, Integer count, String path);

    /**
     * 套餐逻辑删除
     * @param id 套餐id
     * @param path url
     * @return
     */
    public Result deleteCombo(Integer id, String path);



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
    public Result addStoreCombo(String factoryId, String storeId, Double price, String name, List<SimpTableInfoDto> info, String path);

    /**
     * 查看组合
     * @param storeId 店家ID
     * @param factoryId 厂家ID
     * @param path url路径
     * @return
     */
    public Result listComboByStore(String storeId,String factoryId, String path);

    /**
     * 删除组合
     * @param comboId 配套ID
     * @param path 厂家
     * @return
     */
    public Result deleteStoreCombo(Integer comboId, String path);
}
