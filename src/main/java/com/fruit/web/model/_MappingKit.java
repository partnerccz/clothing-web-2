package com.fruit.web.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("a_permission", "id", Permission.class);
		arp.addMapping("a_role", "id", Role.class);
		arp.addMapping("a_user", "id", User.class);
		arp.addMapping("b_banner", "id", Banner.class);
		arp.addMapping("b_product", "id", Product.class);
		arp.addMapping("b_product_img", "id", ProductImg.class);
		arp.addMapping("b_product_keyword", "id", ProductKeyword.class);
		arp.addMapping("b_product_market", "id", ProductMarket.class);
		arp.addMapping("b_product_recommend", "id", ProductRecommend.class);
		arp.addMapping("b_product_standard", "id", ProductStandard.class);
		arp.addMapping("b_product_type", "id", ProductType.class);
		arp.addMapping("b_type", "id", Type.class);
		arp.addMapping("b_type_group", "id", TypeGroup.class);
	}
}

