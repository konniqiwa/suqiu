package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class CreatePeopleModel implements Serializable {

    private List<CreatePeopleRecommendModel> peopleRecommend;

    public List<CreatePeopleRecommendModel> getPeopleRecommend() {
        return peopleRecommend;
    }

    public void setPeopleRecommend(List<CreatePeopleRecommendModel> peopleRecommend) {
        this.peopleRecommend = peopleRecommend;
    }
}
