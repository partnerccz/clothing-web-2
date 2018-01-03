package com.fruit.web.model;

import com.fruit.web.model.base.BaseCartProduct;
import com.fruit.web.util.Common;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CartProduct extends BaseCartProduct<CartProduct> {
	public static final CartProduct dao = new CartProduct().dao();

	/**
	 * 删除购物车商品
	 * @param uid
	 * @param ids
	 */
	public void removeCartProduct(int uid, Integer[] ids) {
		Db.update("delete from b_cart_product where uid=? and product_standard_id in ("+ Common.arrayToSqlIn(ids)+") ", uid);
	}

	/**
	 * 添加购物车商品
	 * @param uid
	 * @param standardId
	 * @param buyNum
	 * @param remark
	 */
	public void addProduct(int uid, int standardId, int buyNum, String remark) {
		Record record = Db.findById("b_cart_product", uid);
		if (record != null) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO b_cart_product(uid, product_standard_id, buy_num, remark, create_time, update_time) ")
					.append("VALUES (?,?,?,?,NOW(),NOW()) ")
					.append("ON DUPLICATE KEY UPDATE buy_num= ?, remark=?, update_time=now()");
			Db.update(sql.toString(), uid, standardId, buyNum, remark, buyNum, remark);
		}else{

		}
	}
}
