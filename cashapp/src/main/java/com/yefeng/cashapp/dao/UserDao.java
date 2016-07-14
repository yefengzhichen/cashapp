package com.yefeng.cashapp.dao;

import com.yefeng.cashapp.model.User;

public interface UserDao {
	public void save(User user);
	public boolean isValid(User user);
	public void updatePassword(User user);
	public User get(String name);
}
