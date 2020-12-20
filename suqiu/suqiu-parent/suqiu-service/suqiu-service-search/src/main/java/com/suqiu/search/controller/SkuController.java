package com.suqiu.search.controller;

import com.suqiu.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/search")
@CrossOrigin
public class SkuController {
    @Autowired
    private SkuService skuService;

    /**
     * 搜索
     * @param searchMap
     * @return
     */
    @GetMapping
    public Map<String,Object> search(@RequestParam(required = false) Map<String,String> searchMap){
        return  skuService.search(searchMap);
    }

    @GetMapping("/import")
    public Result importData() {

        skuService.importData();
        return new Result(true, StatusCode.OK,"数据导入es成功");
    }
}
