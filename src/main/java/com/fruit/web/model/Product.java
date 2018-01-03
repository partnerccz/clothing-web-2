package com.fruit.web.model;

import com.fruit.web.model.base.BaseProduct;
import com.fruit.web.util.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Product extends BaseProduct<Product> {
	public static final Product dao = new Product().dao();
	public static final int STATUS_USE = 1;
	public static final int STATUS_UNUSED = 0;
	public static final int STATUS_DELETE = -1;
	private static final String PRODUCT_LIST_SELECT = "p.id,p.img,p.name,p.measure_unit,ps.id standard_id,ps.name standard_name,ps.sub_title,p.week_sell_num,ps.original_price,ps.sell_price";

	/**
	 * 按照周销量排行，获取N条数据
	 * @return
	 */
	public List<Product> listBuyNum(int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
			.append("FROM b_product p JOIN (")
			.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")// 优先取默认规格，如果没设置默认规格，按照创建时间取第一个，则此处的limit防止mysql忽略order by
			.append(") ps ON (p.id = ps.product_id)")
			.append("WHERE p.`status`=? ")
			.append("GROUP BY p.id ")
			.append("ORDER BY p.week_sell_num desc ")
			.append("LIMIT ?,? ");
		return find(sql.toString(), ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照更新时间排序，获取前N条数据
	 * @return
	 */
	public List<Product> listUpdate(int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.update_time,").append(PRODUCT_LIST_SELECT).append(" ")
				.append("FROM b_product p JOIN (")
				.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")// 此处的limit防止mysql忽略order by
				.append(") ps ON (p.id = ps.product_id)")
				.append("WHERE p.`status`=? ")
				.append("GROUP BY p.id ")
				.append("ORDER BY p.update_time desc ")
				.append("LIMIT ?,? ");
		return find(sql.toString(), ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照推荐获取N条数据
	 * @param recommendType
	 * @return
	 */
	public List<Product> listRecommend(int recommendType, int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
			.append("FROM b_product p JOIN b_product_recommend pr ON (pr.type=? AND p.id = pr.product_id) ")
			.append("JOIN (")
			.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")
			.append(") ps ON (p.id = ps.product_id) ")
			.append("WHERE p.`status`=? ")
			.append("GROUP BY p.id ")
			.append("ORDER BY p.sort desc ")
			.append("LIMIT ?,? ");
		return find(sql.toString(), recommendType, ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照关键字获取前N条数据
	 * @param keyword
	 * @return
	 */
	public List<Product> listSearch(String keyword, int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
			.append("FROM b_product p JOIN b_product_keyword pk ON (pk.keyword=? AND p.id = pk.product_id) ")
			.append("JOIN (")
			.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")
			.append(") ps ON (p.id = ps.product_id) ")
			.append("WHERE p.`status`=? ")
			.append("GROUP BY p.id ")
			.append("ORDER BY p.sort DESC ")
			.append("LIMIT ?,? ");
		return find(sql.toString(), keyword, ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照类型分页获取商品
	 * @param typeId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Product> listType(int typeId, int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
				.append("FROM b_product p JOIN b_product_type pt ON (pt.type_id=? AND p.id = pt.product_id) ")
				.append("JOIN (")
				.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")
				.append(") ps ON (p.id = ps.product_id) ")
				.append("WHERE p.`status`=? ")
				.append("GROUP BY p.id ")
				.append("ORDER BY p.sort DESC ")
				.append("LIMIT ?,? ");
		return find(sql.toString(), typeId, ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照组类型分页获取商品
	 * @param groupTypeId
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Product> listGroupType(int groupTypeId, int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
				.append("FROM b_product p JOIN b_product_type pt ON (p.id = pt.product_id) ")
				.append("JOIN b_type t ON (t.group_id=? AND pt.type_id = t.id) ")
				.append("JOIN (")
				.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")
				.append(") ps ON (p.id = ps.product_id) ")
				.append("WHERE p.`status`=? ")
				.append("GROUP BY p.id ")
				.append("ORDER BY p.sort DESC ")
				.append("LIMIT ?,? ");
		return find(sql.toString(), groupTypeId, ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 默认无条件获取商品列表
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Product> list(int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
				.append("FROM b_product p ")
				.append("JOIN (")
				.append("SELECT * FROM b_product_standard WHERE `status`=? ORDER BY is_default DESC,create_time LIMIT 100000000")
				.append(") ps ON (p.id = ps.product_id) ")
				.append("WHERE p.`status`=? ")
				.append("GROUP BY p.id ")
				.append("ORDER BY p.sort DESC ")
				.append("LIMIT ?,? ");
		return find(sql.toString(), ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}

	/**
	 * 按照规格id获取商品详情
	 * @param ids
	 * @return
	 */
	public List<Product> listByStandardIds(Integer[] ids) {
		if(ids == null || ids.length == 0) {
			return new ArrayList<>();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(" ")
				.append("FROM b_product p JOIN b_product_standard ps ON (p.id = ps.product_id) ")
				.append("WHERE ps.`status`=? AND p.`status`=? AND ps.id IN (" + Common.arrayToSqlIn(ids) + ") ");
		return find(sql.toString(), ProductStandard.STATUS_USE, Product.STATUS_USE);
	}

	/**
	 * 获取用户购物车商品
	 * @param uid
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Product> listCartProduct(int uid, int pageSize, int pageNum) {
		int start = pageSize * (pageNum - 1);
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ").append(PRODUCT_LIST_SELECT).append(",cp.buy_num,cp.remark ")
				.append("FROM (b_product p JOIN b_product_standard ps ON (p.id = ps.product_id))  JOIN b_cart_product cp ON cp.product_standard_id = ps.id ")
				.append("WHERE cp.uid=? and ps.`status`=? AND p.`status`=? ")
				.append("ORDER BY cp.update_time DESC ")
				.append("LIMIT ?,? ");
		System.out.println(sql);
		return find(sql.toString(), uid, ProductStandard.STATUS_USE, Product.STATUS_USE, start, pageSize);
	}
}
