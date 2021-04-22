package com.suqiu.goods.pojo;

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
