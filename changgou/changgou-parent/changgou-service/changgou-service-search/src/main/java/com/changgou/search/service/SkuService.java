package com.changgou.search.service;

import java.util.Map;

public interface SkuService {
    /**
     * 数据导入es
     */
     void importData();
     Map<String,Object> search(Map<String,String> searchMap);
}
