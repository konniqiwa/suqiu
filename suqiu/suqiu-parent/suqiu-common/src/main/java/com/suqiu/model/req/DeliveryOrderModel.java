package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class DeliveryOrderModel implements Serializable {

    private List<DeliveryOrderInfoModel> list;

    public List<DeliveryOrderInfoModel> getList() {
        return list;
    }

    public void setList(List<DeliveryOrderInfoModel> list) {
        this.list = list;
    }
}
