package com.notearena.bd.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notearena.bd.model.UserForm;

@Repository
public class UserDAOImpl implements UserDAO{


	@Autowired
	private SessionFactory sessionFactory;
	


	
	public UserForm getUser(long userId) {
		return (UserForm) sessionFactory.getCurrentSession().get(
				UserForm.class, userId);
	}

	public List<UserForm> getUserList() {
		return sessionFactory.getCurrentSession().createQuery("from UserForm")
				.list();
		
	}

	public String updateUser(UserForm user) {
		sessionFactory.getCurrentSession().update(user);
		return user.toString();
	}

	public UserForm createUser(UserForm user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
	}

	public String deleteUser(UserForm user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(user);
		return user.toString();
	}

}
