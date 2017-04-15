package com.pr.expense.data;

import java.util.Date;

/**
 * @Author Rahul Gaur.
 * Created on 4/1/2017
 */
public class Key1 extends Key {

	private Double amount;

	public Key1(Integer accountId, Date date, Double amount) {
		super(accountId, date);
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Key1 key1 = (Key1) o;

		if (!accountId.equals(key1.accountId))
			return false;
		if (!date.equals(key1.date))
			return false;
		return amount.equals(key1.amount);

	}

	@Override
	public int hashCode() {
		int result = accountId.hashCode();
		result = 31 * result + date.hashCode();
		result = 31 * result + amount.hashCode();
		return result;
	}
}
