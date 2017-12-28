package com.fruit.web.model;

import com.fruit.web.model.base.BaseRole;
import com.fruit.web.util.Common;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Role extends BaseRole<Role> {
	public static final Role dao = new Role().dao();
	private Logger log = Logger.getLogger(getClass());

	/**
	 * 根据用户用户uid查询用户角色列表
	 * @param uid
	 * @return
	 */
	public List<Role> getRoleByUid(int uid) {
		return find("SELECT r.* FROM `a_user` u, `a_user_role` ur, `a_role` r WHERE u.`id` = ur.`user_id` AND r.`id` = ur.`role_id` AND u.`id` = ?", uid);
	}

	/**
	 * 分页查询角色列表
	 * @param roleName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<Role> getData(String roleName, int pageNum, int pageSize, String orderBy, boolean isASC) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("from a_role where 1=1 ");
		if(StrKit.notBlank(roleName)){
			sql.append("and role_name like ? ");
			params.add("%" + roleName + "%");
		}
		orderBy = StrKit.isBlank(orderBy)?"create_time":orderBy;
		sql.append("order by "+orderBy+" "+(isASC?"":"desc "));
		return paginate(pageNum, pageSize, "select * ", sql.toString(), params.toArray());
	}

	/**
	 * 根据角色id删除数据
	 */
	public boolean delete(String roleId){
		log.info("删除数据：roleId=" + roleId);
		return Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				Db.update("delete from a_role_permission where role_id = ?", roleId);
				Db.update("delete from a_role where id = ?", roleId);
				return true;
			}
		});
	}

	/**
	 * 保存角色（如果关联的权限不为空，会同时保存关联的权限）
	 * @param model
	 * @param menuIds
	 * @param permissionIds
	 */
	public boolean save(Role model, String[] menuIds, String[] permissionIds) {
		return Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				if(model.getId() == null){
					model.setCreateTime(new Date());
				}
				model.setUpdateTime(new Date());
				if(model.getId() == null){
					model.save();//保存角色
				}else{
					model.update();//更新角色
				}
				Db.update("delete from a_role_menu where role_id = ?", model.getId());
				Db.update("delete from a_role_permission where role_id = ?", model.getId());
				if(menuIds != null && menuIds.length > 0){
					List<Object[]> params = new ArrayList<>();
					for(String menuId : menuIds) {
						params.add(new Object[]{model.getId(), menuId});
					}
					String sql = "insert into a_role_menu(role_id, menu_id) values(?,?)";
					Db.batch(sql, Common.listTo2Array(params), params.size());
				}
				if(permissionIds != null && permissionIds.length > 0){
					List<Object[]> params = new ArrayList<>();
					for(String permissionId : permissionIds) {
						params.add(new Object[]{model.getId(), permissionId});
					}
					String sql = "insert into a_role_permission(role_id, permission_id) values(?,?)";
					Db.batch(sql, Common.listTo2Array(params), params.size());
				}
				return true;
			}
		});
	}

	/**
	 * 根据用户用户uid查询用户角色id列表
	 * @param uid
	 * @return
	 */
	public List<Integer> getRoleIdsByUserId(int uid) {
		return Db.query("SELECT r.id FROM `a_user` u, `a_user_role` ur, `a_role` r WHERE u.`id` = ur.`user_id` AND r.`id` = ur.`role_id` AND u.`id` = ?", uid);
	}
}
