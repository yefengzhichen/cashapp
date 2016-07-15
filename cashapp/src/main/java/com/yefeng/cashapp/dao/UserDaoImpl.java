package com.yefeng.cashapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.yefeng.cashapp.model.User;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void save(User user) {
		getSession().save(user);
	}
	
	@Override
	public boolean isValid(User user) {
		String name = user.getName();
		String hql = "select password from User where name = ?";
		List<String> result = getSession().createQuery(hql).setParameter(0, name).getResultList();
		String realPassword = result.get(0);
		if (user.getPassword().equals(realPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public void updatePassword(User user) {
		String name = user.getName();
		String password = user.getPassword();
//		String hql = "update User u set u.password = ? where u.name = ?";
//		getSession().createQuery(hql).setParameter(0, password).setParameter(1, name);
//		推荐下面的命名参数方式
		String hql2 = "update User u set u.password=:password where u.name=:name";
		Query query2 = getSession().createQuery(hql2);
		query2.setParameter("password", password);
		query2.setParameter("name", name);
		query2.executeUpdate();
	}

	@Override
	public User get(String name) {
		String hql = "select u from User u where name = ?";
		List<User> result = getSession().createQuery(hql).setParameter(0, name).getResultList();
		return result.get(0);
	}
}
