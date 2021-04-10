package com.suqiu.goods.pojo;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:admin
 * @Description:SmsPeopleRecommend构建
 * @Date 2019/6/14 19:13
 *****/
@Table(name="tb_sms_people_recommend")
public class SmsPeopleRecommend implements Serializable{

	@Id
    @Column(name = "product_id")
	private Long productId;//商品id

    @Column(name = "product_name")
	private String productName;//商品名称

    @Column(name = "people_recommend_status")
	private Integer peopleRecommendStatus;//是否人气推荐

    @Column(name = "people_sort")
	private Long peopleSort;//排序

    @Column(name = "new_recommend_status")
	private Integer newRecommendStatus;//是否新品推荐

    @Column(name = "new_sort")
	private Long newSort;//排序



	//get方法
	public Long getProductId() {
		return productId;
	}

	//set方法
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	//get方法
	public String getProductName() {
		return productName;
	}

	//set方法
	public void setProductName(String productName) {
		this.productName = productName;
	}
	//get方法
	public Integer getPeopleRecommendStatus() {
		return peopleRecommendStatus;
	}

	//set方法
	public void setPeopleRecommendStatus(Integer peopleRecommendStatus) {
		this.peopleRecommendStatus = peopleRecommendStatus;
	}
	//get方法
	public Long getPeopleSort() {
		return peopleSort;
	}

	//set方法
	public void setPeopleSort(Long peopleSort) {
		this.peopleSort = peopleSort;
	}
	//get方法
	public Integer getNewRecommendStatus() {
		return newRecommendStatus;
	}

	//set方法
	public void setNewRecommendStatus(Integer newRecommendStatus) {
		this.newRecommendStatus = newRecommendStatus;
	}
	//get方法
	public Long getNewSort() {
		return newSort;
	}

	//set方法
	public void setNewSort(Long newSort) {
		this.newSort = newSort;
	}


}
