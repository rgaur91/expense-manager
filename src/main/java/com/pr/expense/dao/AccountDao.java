package com.pr.expense.dao;

import com.pr.expense.dao.model.AccountModel;

import java.util.List;

/**
 * @Author Rahul Gaur.
 * Created on 4/2/2017
 */
public interface AccountDao {

	List<AccountModel> getAllAccounts();
}
