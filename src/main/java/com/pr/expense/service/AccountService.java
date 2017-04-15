package com.pr.expense.service;

/**
 * Created by jhanay on 1/15/2017.
 */
public interface AccountService {


    String getAllAccounts();
	String insertAccount(Integer id, String name);
	String updateAccount(Integer id, String name);
}
