package com.pr.expense.dao.impl;

import com.pr.expense.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
public class HibernateBaseDao<E,K extends Serializable> implements BaseDao<E, K> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public E findById(K id) {
//		return sessionFactory.getCurrentSession().get(E.class, id);
		return null;//Todo
	}

	@Override
	public void saveOrUpdate(E entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
}
