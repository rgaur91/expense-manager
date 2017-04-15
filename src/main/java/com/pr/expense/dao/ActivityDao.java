package com.pr.expense.dao;

import com.pr.expense.dao.model.TransactionsModel;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
public interface ActivityDao {

	List<TransactionsModel> getTransactions(int hash1, int hash2);
}
