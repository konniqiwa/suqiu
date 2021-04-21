package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class IsNewRecommendModel implements Serializable {

    private int recommendStatus;

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }
}
