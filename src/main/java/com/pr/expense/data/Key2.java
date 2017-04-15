package com.pr.expense.data;

import java.util.Date;

/**
 * @Author Rahul Gaur.
 * Created on 4/1/2017
 */
public class Key2 extends Key {

	private Integer categoryId;

	private Integer subCategoryId;

	public Key2(Integer accountId, Date date, Integer categoryId, Integer subCategoryId) {
		super(accountId, date);
		this.categoryId = categoryId;
		this.subCategoryId = subCategoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Key2 key2 = (Key2) o;

		if (!accountId.equals(key2.accountId))
			return false;
		if (!date.equals(key2.date))
			return false;
		if (!categoryId.equals(key2.categoryId))
			return false;
		return subCategoryId.equals(key2.subCategoryId);
	}

	@Override
	public int hashCode() {
		int result = accountId.hashCode();
		result = 31 * result + date.hashCode();
		result = 31 * result + categoryId.hashCode();
		result = 31 * result + subCategoryId.hashCode();
		return result;
	}
}
