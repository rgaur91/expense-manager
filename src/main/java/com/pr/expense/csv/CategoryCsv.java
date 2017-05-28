package com.pr.expense.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pr.commons.csv.CsvField;

/**
 * @Author Rahul Gaur.
 * Created on 5/28/2017
 */
public class CategoryCsv {

	@JsonProperty("Category")
	@CsvField(sequence = 1)
	private String category;

	@JsonIgnore
	private Integer categoryId;

	@JsonProperty(value = "SubCategory")
	@CsvField(sequence = 2, defaultValue = "NA")
	private String subCategory;

	@JsonIgnore
	private Integer subCategoryId;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
}
