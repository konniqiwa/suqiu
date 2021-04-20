package com.suqiu.model.res;

import com.suqiu.goods.pojo.SmsPeopleRecommend;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class SmsPeopleRecommendListDTO implements Serializable {

    private List<SmsPeopleRecommend> list;

    private Long total;

    public List<SmsPeopleRecommend> getList() {
        return list;
    }

    public void setList(List<SmsPeopleRecommend> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
