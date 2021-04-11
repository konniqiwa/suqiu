package com.suqiu.model.res;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class SpuSpecDTO implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
