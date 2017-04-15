package com.pr.expense.dao;

import com.pr.expense.dao.model.CategoryModel;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
public interface CategoryDao {

	List<CategoryModel> getAllCategories();
}