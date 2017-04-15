package com.pr.expense.dao.model;

import javax.persistence.*;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
@Entity
@Table(name = "sub_category")
public class SubCategoryModel {
	private Integer idSubCategory;
	private String name;
	private String description;
	private CategoryModel category;

	@Id
	@Column(name = "id_sub_category")
	public Integer getIdSubCategory() {
		return idSubCategory;
	}

	public void setIdSubCategory(Integer idSubCategory) {
		this.idSubCategory = idSubCategory;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SubCategoryModel that = (SubCategoryModel) o;

		if (idSubCategory != null ? !idSubCategory.equals(that.idSubCategory) : that.idSubCategory != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idSubCategory != null ? idSubCategory.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}
}
