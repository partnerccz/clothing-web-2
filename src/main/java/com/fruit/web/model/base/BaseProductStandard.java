package com.fruit.web.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseProductStandard<M extends BaseProductStandard<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setProductId(java.lang.Integer productId) {
		set("product_id", productId);
	}

	public java.lang.Integer getProductId() {
		return getInt("product_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return getStr("name");
	}

	public void setSubTitle(java.lang.String subTitle) {
		set("sub_title", subTitle);
	}

	public java.lang.String getSubTitle() {
		return getStr("sub_title");
	}

	public void setOriginalPrice(java.math.BigDecimal originalPrice) {
		set("original_price", originalPrice);
	}

	public java.math.BigDecimal getOriginalPrice() {
		return get("original_price");
	}

	public void setSellPrice(java.math.BigDecimal sellPrice) {
		set("sell_price", sellPrice);
	}

	public java.math.BigDecimal getSellPrice() {
		return get("sell_price");
	}

	public void setWeightPrice(java.math.BigDecimal weightPrice) {
		set("weight_price", weightPrice);
	}

	public java.math.BigDecimal getWeightPrice() {
		return get("weight_price");
	}

	public void setCostPrice(java.math.BigDecimal costPrice) {
		set("cost_price", costPrice);
	}

	public java.math.BigDecimal getCostPrice() {
		return get("cost_price");
	}

	public void setShippingFee(java.math.BigDecimal shippingFee) {
		set("shipping_fee", shippingFee);
	}

	public java.math.BigDecimal getShippingFee() {
		return get("shipping_fee");
	}

	public void setCartonWeight(java.lang.Double cartonWeight) {
		set("carton_weight", cartonWeight);
	}

	public java.lang.Double getCartonWeight() {
		return getDouble("carton_weight");
	}

	public void setFruitWeight(java.lang.Double fruitWeight) {
		set("fruit_weight", fruitWeight);
	}

	public java.lang.Double getFruitWeight() {
		return getDouble("fruit_weight");
	}

	public void setGrossWeight(java.lang.Double grossWeight) {
		set("gross_weight", grossWeight);
	}

	public java.lang.Double getGrossWeight() {
		return getDouble("gross_weight");
	}

	public void setPurchaseQuantityMin(java.lang.Integer purchaseQuantityMin) {
		set("purchase_quantity_min", purchaseQuantityMin);
	}

	public java.lang.Integer getPurchaseQuantityMin() {
		return getInt("purchase_quantity_min");
	}

	public void setPurchaseQuantityMax(java.lang.Integer purchaseQuantityMax) {
		set("purchase_quantity_max", purchaseQuantityMax);
	}

	public java.lang.Integer getPurchaseQuantityMax() {
		return getInt("purchase_quantity_max");
	}

	public void setBuyStartTime(java.util.Date buyStartTime) {
		set("buy_start_time", buyStartTime);
	}

	public java.util.Date getBuyStartTime() {
		return get("buy_start_time");
	}

	public void setBuyEndTime(java.util.Date buyEndTime) {
		set("buy_end_time", buyEndTime);
	}

	public java.util.Date getBuyEndTime() {
		return get("buy_end_time");
	}

	public void setSortPurchase(java.lang.Integer sortPurchase) {
		set("sort_purchase", sortPurchase);
	}

	public java.lang.Integer getSortPurchase() {
		return getInt("sort_purchase");
	}

	public void setSortSoldOut(java.lang.Integer sortSoldOut) {
		set("sort_sold_out", sortSoldOut);
	}

	public java.lang.Integer getSortSoldOut() {
		return getInt("sort_sold_out");
	}

	public void setSortSplit(java.lang.Integer sortSplit) {
		set("sort_split", sortSplit);
	}

	public java.lang.Integer getSortSplit() {
		return getInt("sort_split");
	}

	public void setStock(java.lang.Integer stock) {
		set("stock", stock);
	}

	public java.lang.Integer getStock() {
		return getInt("stock");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setIsDefault(java.lang.Integer isDefault) {
		set("is_default", isDefault);
	}

	public java.lang.Integer getIsDefault() {
		return getInt("is_default");
	}

	public void setPurchaserUid(java.lang.Integer purchaserUid) {
		set("purchaser_uid", purchaserUid);
	}

	public java.lang.Integer getPurchaserUid() {
		return getInt("purchaser_uid");
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
