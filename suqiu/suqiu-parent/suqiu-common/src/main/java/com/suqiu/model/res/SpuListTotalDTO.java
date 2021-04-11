package com.suqiu.model.res;

import java.io.Serializable;
import java.util.List;

/**
 * @author suqiu
 */
public class SpuListTotalDTO implements Serializable {
    private List<SpuListDTO> list;

    private Long total;

    public List<SpuListDTO> getList() {
        return list;
    }

    public void setList(List<SpuListDTO> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
