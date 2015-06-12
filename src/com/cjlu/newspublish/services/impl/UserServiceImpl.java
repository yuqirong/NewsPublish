package com.cjlu.newspublish.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjlu.newspublish.daos.impl.CommentDaoImpl;
import com.cjlu.newspublish.daos.impl.UserDaoImpl;
import com.cjlu.newspublish.daos.impl.VisitorCounterDaoImpl;
import com.cjlu.newspublish.models.User;
import com.cjlu.newspublish.services.UserService;
import com.cjlu.newspublish.utils.ValidateUtils;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Autowired
	private UserDaoImpl userDao;
	@Autowired
	private CommentDaoImpl commentDao;
	@Autowired
	private VisitorCounterDaoImpl visitorCounterDao;
	/**
	 * 检测用户名是否被占用
	 */
	public boolean isTokenUp(String str) {
		List<User> list = userDao.isTokenUp(str);
		if (!ValidateUtils.isValid(list)) {
			return false;
		}
		return true;
	}

	/**
	 * 检测是否为用户
	 */
	@Override
	public User isUser(String username, String password) {
		User user = userDao.isUser(username, password);
		return user;
	}

	@Override
	public void deleteUser(Integer id) {
		if (id != null && id > 0) {
			commentDao.deleteAllCommentByUserId(id);
			visitorCounterDao.deleteCounterByUserId(id);
			User user = userDao.getEntity(id);
			if (user != null) {
				userDao.deleteEntity(user);
			}
		}
	}
}
