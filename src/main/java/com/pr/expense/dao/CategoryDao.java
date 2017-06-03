package com.pr.expense.dao;

import com.pr.expense.dao.model.CategoryModel;
import com.pr.expense.dao.model.SubCategoryModel;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
public interface CategoryDao {

	List<CategoryModel> getAllCategories();

	void save(CategoryModel categoryModel);

	List<SubCategoryModel> getAllSubCategory();
}
