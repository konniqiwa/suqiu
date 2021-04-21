package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class IsShowStatusModel implements Serializable {

    private List<Integer> ids;

    private int showStatus;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }
}
