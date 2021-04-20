package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class NewRecommendModel extends BasePageModel implements Serializable {

    private String brandName;

    private int recommendStatus;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }
}
