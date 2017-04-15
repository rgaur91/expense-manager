package com.pr.expense.dao.model;

import javax.persistence.*;

/**
 * @Author Rahul Gaur.
 * Created on 3/29/2017
 */
@Entity
@Table(name = "party")
public class PartyModel {
	private Integer idParty;
	private String name;
	private String description;

	@Id
	@Column(name = "id_party")
	public Integer getIdParty() {
		return idParty;
	}

	public void setIdParty(Integer idParty) {
		this.idParty = idParty;
	}

	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PartyModel that = (PartyModel) o;

		if (idParty != null ? !idParty.equals(that.idParty) : that.idParty != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = idParty != null ? idParty.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}
}
