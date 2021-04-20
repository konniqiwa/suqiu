package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class CreateNewRecommendModel implements Serializable {

    private Long brandId;

    private String brandName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
