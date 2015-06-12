package com.cjlu.newspublish.services;

import com.cjlu.newspublish.models.User;

public interface UserService extends BaseService<User>{
	
	public boolean isTokenUp(String str);
	
	public User isUser(String username,String password);

	public void deleteUser(Integer id);

}
