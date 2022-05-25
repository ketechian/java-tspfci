package com.example.javatspfci.code.controller;


import com.example.javatspfci.code.entity.dto.AddStoreComboDto;
import com.example.javatspfci.code.result.Result;
import com.example.javatspfci.code.stencil.ComboStencil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2022-05-23
 */
@RestController
@RequestMapping("/code/combo")
public class ComboController {

    @Resource
    private ComboStencil comboStencil;

    @PostMapping("/addStoreCombo")
    public Result addStoreCombo(@RequestBody AddStoreComboDto addStoreComboDto){
        return comboStencil.addStoreCombo(
                addStoreComboDto.getFactoryId(),
                addStoreComboDto.getStoreId(),
                addStoreComboDto.getPrice(),
                addStoreComboDto.getName(),
                addStoreComboDto.getInfo(),"/code/combo/addStoreCombo");
    }

    @GetMapping("/listComboByStore")
    public Result listComboByStore(@RequestParam String storeId,
                                   @RequestParam String factoryId){
        return comboStencil.listComboByStore(storeId, factoryId, "/code/combo/listComboByStore");
    }

    @PostMapping("/deleteStoreCombo")
    public Result deleteStoreCombo(@RequestParam Integer comboId){
        return comboStencil.deleteStoreCombo(comboId, "/code/combo/deleteStoreCombo");
    }
}
