package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class PeopleRecommendModel extends BasePageModel implements Serializable {

    private String productName;

    private int recommendStatus;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }
}
