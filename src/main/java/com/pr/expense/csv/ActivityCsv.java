package com.pr.expense.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pr.commons.csv.CsvField;
import com.pr.expense.data.Key;
import com.pr.expense.data.Key1;
import com.pr.expense.data.Key2;
import com.pr.expense.type.ActionType;

import java.util.Date;

/**
 * @Author Rahul Gaur.
 * Created on 3/26/2017
 */
public class ActivityCsv {

	@JsonProperty("Date")
	@CsvField(sequence = 1, dateFormat = "yyyy-MM-dd")
	private Date date;

	@JsonProperty("Amount")
	@CsvField(sequence = 2)
	private Double amount;

	@JsonProperty("Category")
	@CsvField(sequence = 3)
	private String category;

	@JsonIgnore
	private Integer categoryId;

	@JsonProperty(value = "SubCategory")
	@CsvField(sequence = 4, defaultValue = "NA")
	private String subCategory;

	@JsonIgnore
	private Integer subCategoryId;

	@JsonProperty(value = "Payment Method")
	@CsvField(sequence = 5, defaultValue = "Electronic Transfer")
	private String paymentMethod;

	@JsonProperty("Description")
	@CsvField(sequence = 6, required = false)
	private String description;

	@JsonProperty(value = "Ref/Check")
	@CsvField(sequence = 7, required = false)
	private String ref;

	@JsonProperty("Payee/Payer")
	@CsvField(sequence = 8)
	private String party;

	@JsonProperty("Account")
	@CsvField(sequence = 9)
	private String account;

	@JsonIgnore
	private Integer accountId;

	private ActivityCsv existing;

	private int actionType = ActionType.ADD.getId();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	@JsonIgnore
	public Key getKey1(){
		return new Key1(accountId, date, amount);
	}

	@JsonIgnore
	public Key getKey2(){
		return new Key2(accountId, date, categoryId, subCategoryId);
	}

	public void setExisting(ActivityCsv existing) {
		this.existing = existing;
	}

	public ActivityCsv getExisting() {
		return existing;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public int getActionType() {
		return actionType;
	}
}
