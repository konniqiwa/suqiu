package com.suqiu.model.res;

import java.io.Serializable;

/**
 * @author suqiu
 */
public class SpuSpecParamDTO implements Serializable {

    private Long id;

    private Integer lockStock;

    private String lowStock;

    private String pic;

    private Integer price;

    private Long productId;

    private Double promotionPrice;

    private Integer sale;

    private String skuCode;

    private String spData;

    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

    public String getLowStock() {
        return lowStock;
    }

    public void setLowStock(String lowStock) {
        this.lowStock = lowStock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
