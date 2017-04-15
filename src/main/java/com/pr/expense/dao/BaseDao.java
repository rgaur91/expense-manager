package com.pr.expense.dao;

import java.io.Serializable;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
public interface BaseDao<E,K extends Serializable> {

	E findById(K id);

	void saveOrUpdate(E entity);

	void delete(E entity);
}
