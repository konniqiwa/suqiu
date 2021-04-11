package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class BrandListModel extends BasePageModel implements Serializable {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
