package com.suqiu.model.req;

import java.io.Serializable;
import java.util.List;


/**
 * @author suqiu
 */
public class CloseOrderModel implements Serializable {

    private List<Long> ids;

    private String note;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
