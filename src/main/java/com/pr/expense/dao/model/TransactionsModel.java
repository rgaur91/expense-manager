package com.pr.expense.dao.model;

import com.pr.expense.data.Key;
import com.pr.expense.data.Key1;
import com.pr.expense.data.Key2;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
@Entity
@Table(name = "transactions")
public class TransactionsModel {
	private Long idTransaction;
	private Timestamp date;
	private Double amount;
	private String paymentMethod;
	private String description;
	private String ref;
	private CategoryModel category;
	private SubCategoryModel subCategory;
	private PartyModel party;
	private AccountModel account;
	private Integer hash1;
	private Integer hash2;


	@Id
	@Column(name = "id_transaction")
	public Long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}

	@Basic
	@Column(name = "date")
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Basic
	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Basic
	@Column(name = "payment_method")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name = "ref")
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	@Basic
	@Column(name = "hash1")
	public Integer getHash1() {
		return hash1;
	}

	public void setHash1(Integer hash1) {
		this.hash1 = hash1;
	}

	@Basic
	@Column(name = "hash2")
	public Integer getHash2() {
		return hash2;
	}

	public void setHash2(Integer hash2) {
		this.hash2 = hash2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TransactionsModel that = (TransactionsModel) o;

		if (!idTransaction.equals(that.idTransaction))
			return false;
		if (!date.equals(that.date))
			return false;
		if (!amount.equals(that.amount))
			return false;
		if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (ref != null ? !ref.equals(that.ref) : that.ref != null)
			return false;
		if (!hash1.equals(that.hash1))
			return false;
		return hash2.equals(that.hash2);

	}

	@Override
	public int hashCode() {
		int result = idTransaction.hashCode();
		result = 31 * result + date.hashCode();
		result = 31 * result + amount.hashCode();
		result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (ref != null ? ref.hashCode() : 0);
		result = 31 * result + hash1.hashCode();
		result = 31 * result + hash2.hashCode();
		return result;
	}

	@ManyToOne
	@JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "id_sub_category", referencedColumnName = "id_sub_category")
	public SubCategoryModel getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategoryModel subCategory) {
		this.subCategory = subCategory;
	}

	@ManyToOne
	@JoinColumn(name = "id_party", referencedColumnName = "id_party", nullable = false)
	public PartyModel getParty() {
		return party;
	}

	public void setParty(PartyModel party) {
		this.party = party;
	}

	@ManyToOne
	@JoinColumn(name = "id_account", referencedColumnName = "account_id")
	public AccountModel getAccount() {
		return account;
	}

	public void setAccount(AccountModel account) {
		this.account = account;
	}

	@Transient
	public Key getKey1(){
		return new Key1(account.getAccountId(), date, amount);
	}

	@Transient
	public Key getKey2(){
		return new Key2(account.getAccountId(), date, category.getIdCategory(), subCategory.getIdSubCategory());
	}
}
