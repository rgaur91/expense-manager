package com.pr.expense.service.impl;

import com.pr.commons.csv.CSVParser;
import com.pr.commons.csv.LineResult;
import com.pr.commons.csv.ParseResult;
import com.pr.commons.dto.Message;
import com.pr.commons.util.StringUtil;
import com.pr.expense.csv.ActivityCsv;
import com.pr.expense.csv.CategoryCsv;
import com.pr.expense.csv.ExpenseManagerParser;
import com.pr.expense.dao.AccountDao;
import com.pr.expense.dao.ActivityDao;
import com.pr.expense.dao.CategoryDao;
import com.pr.expense.dao.model.AccountModel;
import com.pr.expense.dao.model.CategoryModel;
import com.pr.expense.dao.model.SubCategoryModel;
import com.pr.expense.dao.model.TransactionsModel;
import com.pr.expense.data.Key;
import com.pr.expense.service.CSVIOService;
import com.pr.expense.type.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by jhanay on 1/15/2017.
 */

@Service
public class CSVIOServiceImpl implements CSVIOService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ActivityDao activityDao;

	@Transactional
    public String upload(String csvData) {

		ParseResult<ActivityCsv> parseResult = parse(csvData);
		addOrUpdate(parseResult);
		return "upload.successful";
    }

	@Override
	@Transactional
	public ParseResult<ActivityCsv> validate(String csvData) {
		return parse(csvData);
	}

	@Override
	@Transactional
	public Message uploadCategory(String cavData) {
		ParseResult<CategoryCsv> parseResult = new CSVParser<CategoryCsv>() {
			@Override
			protected void validateData(CategoryCsv categoryCsv, Set<String> error) {
			}
		}.parse(cavData, CategoryCsv.class);
		List<CategoryModel> allCategoryList = categoryDao.getAllCategories();
		Map<String, CategoryModel> allCategories = new HashMap<>(allCategoryList.size());
		Map<String, Map<String, SubCategoryModel>> allSubcategories = new HashMap<>(allCategoryList.size());
		for (CategoryModel categoryModel : allCategoryList) {
			allCategories.put(categoryModel.getName().toLowerCase(), categoryModel);
			HashMap<String, SubCategoryModel> subCategories = new HashMap<>();
			allSubcategories.put(categoryModel.getName().toLowerCase(), subCategories);
			for (SubCategoryModel subCategoryModel : categoryModel.getSubCategories()) {
				subCategories.put(subCategoryModel.getName().toLowerCase(), subCategoryModel);
			}
		}

		for (LineResult<CategoryCsv> lineResult : parseResult.getLineResults()) {
			CategoryCsv data = lineResult.getData();
			CategoryModel categoryModel = allCategories.get(data.getCategory());
			if (categoryModel == null) {
				categoryModel = new CategoryModel();
				categoryModel.setName(data.getCategory());
				categoryDao.save(categoryModel);
				allCategories.put(data.getCategory(),categoryModel);
				allSubcategories.put(data.getCategory(), new HashMap<String, SubCategoryModel>());
			}
			Map<String, SubCategoryModel> category = allSubcategories.get(data.getCategory());
			SubCategoryModel subCategoryModel = category.get(data.getSubCategory());
			if (subCategoryModel == null) {
				subCategoryModel = new SubCategoryModel();
				List<SubCategoryModel> subCategories = categoryModel.getSubCategories();
				if (subCategories == null) {
					subCategories = new LinkedList<>();
					categoryModel.setSubCategories(subCategories);
				}
				category.put(data.getSubCategory(), subCategoryModel);
				subCategories.add(subCategoryModel);
			}
		}
		return new Message("added.successful");
	}

	private ParseResult<ActivityCsv> parse(String csvData){
		ExpenseManagerParser parser = new ExpenseManagerParser();
		ParseResult<ActivityCsv> parseResult = parser.parse(csvData);
		List<AccountModel> allAccountList = accountDao.getAllAccounts();
		Map<String, AccountModel> allAccounts = new HashMap<>(allAccountList.size());
		for (AccountModel accountModel : allAccountList) {
			allAccounts.put(accountModel.getName().toLowerCase(), accountModel);
		}
		List<CategoryModel> allCategoryList = categoryDao.getAllCategories();
		Map<String, CategoryModel> allCategories = new HashMap<>(allCategoryList.size());
		Map<String, Map<String, SubCategoryModel>> allSubcategories = new HashMap<>(allCategoryList.size());
		for (CategoryModel categoryModel : allCategoryList) {
			allCategories.put(categoryModel.getName().toLowerCase(), categoryModel);
			HashMap<String, SubCategoryModel> subCategories = new HashMap<>();
			allSubcategories.put(categoryModel.getName().toLowerCase(), subCategories);
			for (SubCategoryModel subCategoryModel : categoryModel.getSubCategories()) {
				subCategories.put(subCategoryModel.getName().toLowerCase(), subCategoryModel);
			}
		}

		for (LineResult<ActivityCsv> lineResult : parseResult.getLineResults()) {
			ActivityCsv activityCsv = lineResult.getData();
			Set<String> errors = lineResult.getErrors();
			if (StringUtil.isNotNullAndEmpty(activityCsv.getAccount())) {
				AccountModel accountModel = allAccounts.get(activityCsv.getAccount().toLowerCase());
				if (accountModel == null) {
					errors.add("Account");
				} else {
					activityCsv.setAccountId(accountModel.getAccountId());
				}
			}
			CategoryModel categoryModel = allCategories.get(activityCsv.getCategory().toLowerCase());
			if (categoryModel == null) {
				errors.add("Category");
			} else {
				activityCsv.setCategoryId(categoryModel.getIdCategory());
				Map<String, SubCategoryModel> subCategories = allSubcategories
						.get(activityCsv.getCategory().toLowerCase());
				SubCategoryModel subCategoryModel = subCategories.get(activityCsv.getSubCategory().toLowerCase());
				if (subCategoryModel == null) {
					errors.add("SubCategory");
				} else {
					activityCsv.setSubCategoryId(subCategoryModel.getIdSubCategory());
				}
			}
			if(errors.isEmpty()){
				Key key1 = activityCsv.getKey1();
				Key key2 = activityCsv.getKey2();
				List<TransactionsModel> transactions = activityDao.getTransactions(key1.hashCode(), key2.hashCode());
				TransactionsModel match2 = null;
				for (TransactionsModel transaction : transactions) {
					if(transaction.getKey2().equals(key2)) {
						ActivityCsv existing = convert(transaction);
						existing.setActionType(ActionType.DELETE.getId());
						activityCsv.setExisting(existing);
						break;
					} else {
						match2 = transaction;
					}
				}
				if(match2 !=null && activityCsv.getExisting() == null){
					ActivityCsv existing = convert(match2);
					existing.setActionType(ActionType.DELETE.getId());
					activityCsv.setExisting(existing);
				}
			}
		}
		return parseResult;
	}

	private ActivityCsv convert(TransactionsModel transaction) {
		ActivityCsv activityCsv = new ActivityCsv();
		activityCsv.setAccount(transaction.getAccount().getName());
		activityCsv.setDate(transaction.getDate());
		activityCsv.setCategory(transaction.getCategory().getName());
		activityCsv.setSubCategory(transaction.getSubCategory().getName());
		activityCsv.setParty(transaction.getParty().getName());
		activityCsv.setDescription(transaction.getDescription());
		activityCsv.setPaymentMethod(transaction.getPaymentMethod());
		activityCsv.setRef(transaction.getRef());
		return activityCsv;
	}

	private void addOrUpdate(ParseResult<ActivityCsv> parseResult) {

	}

}
