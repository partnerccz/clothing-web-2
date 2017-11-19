package com.fruit.web.model;

import com.fruit.web.model.base.BaseProductStandard;
import com.jfinal.kit.StrKit;

import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ProductStandard extends BaseProductStandard<ProductStandard> {
	public static final ProductStandard dao = new ProductStandard().dao();
	public static final int STATUS_USE = 1;
	public static final int STATUS_UNUSED = 0;
	public static final int STATUS_DELETE = -1;

	public List<ProductStandard> listProductStandard(int productId, String selectFields) {
		if(StrKit.isBlank(selectFields)) {
			selectFields = "*";
		}
		return find("SELECT "+selectFields+" FROM b_product_standard WHERE product_id=? AND status=?", productId, STATUS_USE);
	}
}
