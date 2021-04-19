package com.suqiu.model.res;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class CategoryInfoDTO implements Serializable {

    private Integer id;//分类ID

    private String name;//分类名称

    private Integer goodsNum;//商品数量

    private String isShow;//是否显示

    private String isMenu;//是否导航

    private Integer seq;//排序

    private Integer parentId;//上级ID

    private Integer templateId;//模板ID

    private Integer level;//分类等级

}
