package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class CreatePeopleRecommendModel implements Serializable {

    private Long productId;

    private String productName;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
