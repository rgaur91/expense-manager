package com.pr.expense.type;

/**
 * @Author Rahul Gaur.
 * Created on 4/7/2017
 */
public enum ActionType {
	ADD(1),DELETE(2);

	private int id;

	ActionType(int id) {

		this.id = id;
	}

	public int getId() {
		return id;
	}
}
