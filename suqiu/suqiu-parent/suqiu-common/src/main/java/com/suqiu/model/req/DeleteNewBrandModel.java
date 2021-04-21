package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class DeleteNewBrandModel implements Serializable {

    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
