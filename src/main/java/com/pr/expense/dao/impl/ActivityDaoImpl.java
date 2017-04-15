package com.pr.expense.dao.impl;

import com.pr.expense.dao.ActivityDao;
import com.pr.expense.dao.model.TransactionsModel;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
@Repository
public class ActivityDaoImpl implements ActivityDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<TransactionsModel> getTransactions(int hash1, int hash2) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionsModel.class);
		criteria.add(Restrictions.or(Restrictions.eq("hash1", hash1), Restrictions.eq("hash2", hash2)));
		@SuppressWarnings("unchecked")
		List<TransactionsModel> list = criteria.list();
		return list;
	}
}
