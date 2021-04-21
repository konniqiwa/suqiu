package com.suqiu.model.req;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class IsFactoryStatusModel implements Serializable {

    private int isFactoryStatus;

    public int getIsFactoryStatus() {
        return isFactoryStatus;
    }

    public void setIsFactoryStatus(int isFactoryStatus) {
        this.isFactoryStatus = isFactoryStatus;
    }
}
