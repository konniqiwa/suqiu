package com.suqiu.model;

import java.io.Serializable;

/**
 * @author SUQIU
 */
public class SpuListModel extends BasePageModel implements Serializable {
    private String keyword;

    private Integer publishStatus;

    private Integer verifyStatus;

    private Long productSn;

    private Integer productCategoryId;

    private Long brandId;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Long getProductSn() {
        return productSn;
    }

    public void setProductSn(Long productSn) {
        this.productSn = productSn;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
