package com.fruit.web.model;

import com.fruit.web.model.base.BaseProductMarket;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProductMarket extends BaseProductMarket<ProductMarket> {
	public static final ProductMarket dao = new ProductMarket().dao();

	public ProductMarket getMarket(int productId) {
		return findFirst("SELECT * FROM b_product_market WHERE product_id=? LIMIT 1 ", productId);
	}
}