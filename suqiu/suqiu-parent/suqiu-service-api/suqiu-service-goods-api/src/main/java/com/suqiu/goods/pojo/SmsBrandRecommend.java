package com.suqiu.goods.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/****
 * @Author:admin
 * @Description:SmsBrandRecommend构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_sms_brand_recommend")
public class SmsBrandRecommend implements Serializable{

	@Id
    @Column(name = "brand_id")
	private Long brandId;//id

    @Column(name = "brand_name")
	private String brandName;//品牌名称

    @Column(name = "recommend_status")
	private Integer recommendStatus;//是否推荐

    @Column(name = "sort")
	private Long sort;//排序



	//get方法
	public Long getBrandId() {
		return brandId;
	}

	//set方法
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	//get方法
	public String getBrandName() {
		return brandName;
	}

	//set方法
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	//get方法
	public Integer getRecommendStatus() {
		return recommendStatus;
	}

	//set方法
	public void setRecommendStatus(Integer recommendStatus) {
		this.recommendStatus = recommendStatus;
	}
	//get方法
	public Long getSort() {
		return sort;
	}

	//set方法
	public void setSort(Long sort) {
		this.sort = sort;
	}


}
