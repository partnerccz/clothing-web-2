package com.fruit.web.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProduct<M extends BaseProduct<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public void setFruitType(java.lang.String fruitType) {
		set("fruit_type", fruitType);
	}

	public java.lang.String getFruitType() {
		return getStr("fruit_type");
	}

	public void setCountry(java.lang.String country) {
		set("country", country);
	}

	public java.lang.String getCountry() {
		return getStr("country");
	}

	public void setProvince(java.lang.String province) {
		set("province", province);
	}

	public java.lang.String getProvince() {
		return getStr("province");
	}

	public void setSort(java.lang.Long sort) {
		set("sort", sort);
	}

	public java.lang.Long getSort() {
		return getLong("sort");
	}

	public void setBrand(java.lang.String brand) {
		set("brand", brand);
	}

	public java.lang.String getBrand() {
		return getStr("brand");
	}

	public void setMeasureUnit(java.lang.String measureUnit) {
		set("measure_unit", measureUnit);
	}

	public java.lang.String getMeasureUnit() {
		return getStr("measure_unit");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setFreshTime(java.lang.Integer freshTime) {
		set("fresh_time", freshTime);
	}

	public java.lang.Integer getFreshTime() {
		return getInt("fresh_time");
	}

	public void setFreshExpireTime(java.util.Date freshExpireTime) {
		set("fresh_expire_time", freshExpireTime);
	}

	public java.util.Date getFreshExpireTime() {
		return get("fresh_expire_time");
	}

	public void setImg(java.lang.String img) {
		set("img", img);
	}

	public java.lang.String getImg() {
		return getStr("img");
	}

	public void setFruitDes(java.lang.String fruitDes) {
		set("fruit_des", fruitDes);
	}

	public java.lang.String getFruitDes() {
		return getStr("fruit_des");
	}

	public void setStoreWay(java.lang.String storeWay) {
		set("store_way", storeWay);
	}

	public java.lang.String getStoreWay() {
		return getStr("store_way");
	}

	public void setTotalSellNum(java.lang.Integer totalSellNum) {
		set("total_sell_num", totalSellNum);
	}

	public java.lang.Integer getTotalSellNum() {
		return getInt("total_sell_num");
	}

	public void setWeekSellNum(java.lang.Integer weekSellNum) {
		set("week_sell_num", weekSellNum);
	}

	public java.lang.Integer getWeekSellNum() {
		return getInt("week_sell_num");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}

	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
