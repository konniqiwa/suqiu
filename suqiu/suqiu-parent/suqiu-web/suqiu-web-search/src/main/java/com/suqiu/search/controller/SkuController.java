package com.suqiu.search.controller;


import com.suqiu.search.feign.SkuFeign;
import com.suqiu.search.pojo.SkuInfo;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuFeign skuFeign;




    @GetMapping(value = "/list")
    public String search(@RequestParam(required = false)Map<String,String> searchMap, Model model) {

        Map<String,Object> resultMap = skuFeign.search(searchMap);
        model.addAttribute("result",resultMap);

        Page<SkuInfo> pageInfo = new Page<SkuInfo>(
                        Long.parseLong(resultMap.get("total").toString()),
                        Integer.parseInt(resultMap.get("pageNumber").toString())+1,
                        Integer.parseInt(resultMap.get("pageSize").toString())

        );
        model.addAttribute("pageInfo",pageInfo);

        model.addAttribute("searchMap",searchMap);
        String[] urls = url(searchMap);
        model.addAttribute("url",urls[0]);
        model.addAttribute("sorturl",urls[1]);
        return "search";
    }

    /**
     * 从前端接收url，并拼接相应的属性值（分类，品牌，规格等）返回
     * @param searchMap
     * @return
     */
    public String[] url(Map<String,String> searchMap) {
        String url = "/search/list"; // a/b?id=1&
        String sorturl = "/search/list";
        if (searchMap != null && searchMap.size() > 0) {
            url += "?";
            sorturl += "?";
            for (Map.Entry<String, String> entry : searchMap.entrySet()) {
                String key = entry.getKey();
                if (key.equalsIgnoreCase("pageNum")) {
                    continue;
                }

                String value = entry.getValue();

                url += key+"=" + value + "&";

                if (key.equalsIgnoreCase("sortField") || key.equalsIgnoreCase("sortRule")) {
                    continue;
                }

                sorturl += key+"=" + value + "&";

            }
            url = url.substring(0,url.length() - 1);
            sorturl = sorturl.substring(0,sorturl.length() - 1);

        }
        return new String[]{url,sorturl};
    }
}
