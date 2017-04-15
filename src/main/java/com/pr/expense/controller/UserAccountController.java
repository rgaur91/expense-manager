package com.pr.expense.controller;

import com.pr.expense.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */

@Controller
public class UserAccountController {

	@Autowired
	AccountService accountService;

	@ApiOperation(value = "User Account",
			notes = "This Request is used for getting accounts")
	@RequestMapping(value = "v1/account", method = RequestMethod.GET,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getAccounts(@RequestBody MultipartFile data){

		return accountService.getAllAccounts();
	}

	@RequestMapping(value = "v1/account/{id}", method = RequestMethod.GET,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertAccount(@RequestBody MultipartFile data){

//		return accountService.insertAccount();
		return null;
	}
}
