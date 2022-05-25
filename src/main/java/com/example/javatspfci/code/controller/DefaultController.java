package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.DefaultDto;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.stencil.ComboStencil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author katechian
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/code/default")
public class DefaultController {
    @Autowired
    private ComboStencil comboStencil;

    /**
     * 添加套餐
     *
     * @param comboDto 餐具接收类
     * @return
     */
    @PostMapping("/addCombo")
    public Result addCombo(@RequestBody DefaultDto comboDto) {
        Result result = comboStencil.addCombo(
                comboDto.getDefFactoryId(),
                comboDto.getDefPicture(),
                comboDto.getDefPrice(),
                comboDto.getDefName(),
                comboDto.getDefInfo(),
                comboDto.getDefStock(),
                "/code/default/addCombo"
        );
        return result;
    }

    /**
     * 修改套餐
     *
     * @param comboDto 餐具接收类
     * @return
     */
    @PostMapping("/updateCombo")
    public Result updateCombo(@RequestBody DefaultDto comboDto) {
        Result result = comboStencil.updateCombo(
                comboDto.getDefId(),
                comboDto.getDefFactoryId(),
                comboDto.getDefPicture(),
                comboDto.getDefPrice(),
                comboDto.getDefName(),
                comboDto.getDefInfo(),
                comboDto.getDefStock(),
                "/code/default/updateCombo"
        );
        return result;
    }

    /**
     * 根据id查询套餐
     *
     * @param id 套餐id
     * @return
     */
    @GetMapping("/getCombo")
    public Result getCombo(@RequestParam Integer id) {
        Result result = comboStencil.selectComboById(id, "/code/default/getCombo");
        return result;
    }

    /**
     * 分页查询所有套餐
     *
     * @param page  开始页数
     * @param count 查多少个
     * @return
     */
    @GetMapping("/getAllCombo")
    public Result getCombo(@RequestParam Integer page,
                           @RequestParam Integer count) {
        Result result = comboStencil.listAllComboByPage(page, count, "/code/default/getAllCombo");
        return result;
    }

    /**
     * 根据厂家id查询套餐
     *
     * @param factoryId 厂家id
     * @param page      开始页数
     * @param count     查多少个
     * @return
     */
    @GetMapping("/getComboByFactory")
    public Result getComboByFactory(@RequestParam String factoryId,
                                       @RequestParam Integer page,
                                       @RequestParam Integer count) {
        Result result = comboStencil.listComboByFactory(factoryId, page, count, "/code/default/getComboByFactory");
        return result;
    }

    /**
     * 逻辑删除套餐
     * @param id 套餐id
     * @return
     */
    @PostMapping("/deleteCombo")
    public Result deleteCombo(@RequestParam Integer id) {
        Result result = comboStencil.deleteCombo(id, "/code/default/deleteCombo");
        return result;
    }
}
