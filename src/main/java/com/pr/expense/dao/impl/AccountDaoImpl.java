package com.pr.expense.dao.impl;

import com.pr.expense.dao.AccountDao;
import com.pr.expense.dao.model.AccountModel;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AccountModel> getAllAccounts() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AccountModel.class);
		@SuppressWarnings("unchecked")
		List<AccountModel> list = criteria.list();
		return list;
	}
}
