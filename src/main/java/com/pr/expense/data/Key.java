package com.pr.expense.data;

import java.util.Date;

/**
 * @Author Rahul Gaur.
 * Created on 4/1/2017
 */
public abstract class Key {

	protected Integer accountId;

	protected Date date;

	public Key(Integer accountId, Date date) {
		this.accountId = accountId;
		this.date = date;
	}
}
