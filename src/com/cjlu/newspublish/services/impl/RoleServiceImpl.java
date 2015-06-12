package com.cjlu.newspublish.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.RoleDaoImpl;
import com.cjlu.newspublish.models.security.Right;
import com.cjlu.newspublish.models.security.Role;
import com.cjlu.newspublish.services.RightService;
import com.cjlu.newspublish.services.RoleService;
import com.cjlu.newspublish.utils.ValidateUtils;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Autowired
	private RightService rightService;
	@Autowired
	private RoleDaoImpl roleDao;

	/**
	 * 保存或更新角色
	 */
	@Override
	public void saveOrUpdateRole(Role model, Integer[] ownRightIds) {
		if (!ValidateUtils.isValid(ownRightIds)) {
			model.getRights().clear();
		} else {
			List<Right> rights = rightService.findRightsInRange(ownRightIds);
			model.setRights(new HashSet<Right>(rights));
		}
		this.saveOrUpdateEntity(model);

	}

	/**
	 * 查找不在范围内的角色
	 */
	@Override
	public List<Role> findRolesNotInRange(Set<Role> set) {
		if (set == null || set.size() == 0) {
			return this.findAllEntities();
		} else {
			return roleDao.findRolesNotInRange(set);
		}
	}

	/**
	 * 查找在范围内的角色
	 */
	@Override
	public List<Role> findRolesInRange(Integer[] ownRoleIds) {
		if (ValidateUtils.isValid(ownRoleIds)) {
			return roleDao.findRolesInRange(ownRoleIds);
		}
		return null;
	}

}
