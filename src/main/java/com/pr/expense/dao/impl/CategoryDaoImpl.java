package com.pr.expense.dao.impl;

import com.pr.expense.dao.CategoryDao;
import com.pr.expense.dao.model.CategoryModel;
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
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CategoryModel> getAllCategories() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CategoryModel.class);
		@SuppressWarnings("unchecked")
		List<CategoryModel> list = criteria.list();
		return list;
	}

	@Override
	public void save(CategoryModel categoryModel) {
		sessionFactory.getCurrentSession().saveOrUpdate(categoryModel);
	}
}
