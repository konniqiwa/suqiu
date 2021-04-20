package com.suqiu.model.res;

import com.suqiu.goods.pojo.SmsBrandRecommend;
import com.suqiu.goods.pojo.SmsPeopleRecommend;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class SmsNewRecommendListDTO implements Serializable {

    private List<SmsBrandRecommend> list;

    private Long total;

    public List<SmsBrandRecommend> getList() {
        return list;
    }

    public void setList(List<SmsBrandRecommend> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
