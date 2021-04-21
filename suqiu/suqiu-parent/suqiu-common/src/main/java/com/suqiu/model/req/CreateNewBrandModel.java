package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class CreateNewBrandModel implements Serializable {

    private List<CreateNewRecommendModel> brandRecommend;

    public List<CreateNewRecommendModel> getBrandRecommend() {
        return brandRecommend;
    }

    public void setBrandRecommend(List<CreateNewRecommendModel> brandRecommend) {
        this.brandRecommend = brandRecommend;
    }
}
