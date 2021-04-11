package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class UpdateStatusModel implements Serializable {

    private Long id;

    private Integer type;

    private Integer isRecommend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }
}
