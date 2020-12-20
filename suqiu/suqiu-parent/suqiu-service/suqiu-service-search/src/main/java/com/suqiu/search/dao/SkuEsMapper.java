package com.suqiu.search.dao;

import com.suqiu.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
