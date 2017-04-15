package com.pr.expense.dao.model;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
@Entity
@Table(name = "category")
public class CategoryModel {
	private Integer idCategory;
	private String name;
	private String description;
	private List<SubCategoryModel> subCategories;

	@Id
	@Column(name = "id_category")
	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
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

		CategoryModel that = (CategoryModel) o;

		if (idCategory != null ? !idCategory.equals(that.idCategory) : that.idCategory != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idCategory != null ? idCategory.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@OneToMany(mappedBy = "category")
	public List<SubCategoryModel> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategoryModel> subCategories) {
		this.subCategories = subCategories;
	}
}
