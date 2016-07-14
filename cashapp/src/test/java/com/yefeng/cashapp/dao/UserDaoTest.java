package com.yefeng.cashapp.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yefeng.cashapp.model.User;

public class UserDaoTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

	@Test
	public void saveTest() {
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = new User("Jack", "123456", "basketball, swimming");
		userDao.save(user);
		boolean valid = userDao.isValid(user);
		Assert.assertTrue(valid);			
	}
	@Test	
	public void getTest(){
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = new User("Jack", "123456", "basketball, swimming");
		User user2 = userDao.get("Jack");
		boolean flag = false;
		if (user.getName().equals(user2.getName()) && user.getPassword().equals(user2.getPassword())
				&& user.getDescription().equals(user2.getDescription())) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}
	
	@Test	
	public void updatePasswordTest(){
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = new User("Jack", "999333", "basketball, swimming");
		userDao.updatePassword(user);
		User user2 = userDao.get("Jack");
		boolean flag = false;
		if (user.getPassword().equals(user2.getPassword())) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}
	// public void save(User user);
	// public boolean isValid(User user);
	// public void updatePassword(User user);
	// public User get(String name);
}
