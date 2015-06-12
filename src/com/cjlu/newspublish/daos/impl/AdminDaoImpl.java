package com.cjlu.newspublish.daos.impl;

import org.springframework.stereotype.Repository;

import com.cjlu.newspublish.models.security.Admin;
import com.cjlu.newspublish.utils.DataUtils;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> {

	public Admin isAdmin(String username, String password) {
		String hql = "FROM Admin WHERE username = ? AND password= ?";
		Admin admin = (Admin) getSession().createQuery(hql).setString(0, username)
				.setString(1, DataUtils.md5(password)).uniqueResult();
		return admin;
	}

	public Admin findAdminByUsername(String username) {
		String hql = "FROM Admin a where a.username = ?";
		return (Admin) this.uniqueResult(hql, username);
	}

}
