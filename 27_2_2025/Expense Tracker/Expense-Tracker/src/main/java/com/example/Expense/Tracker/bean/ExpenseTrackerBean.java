package com.example.Expense.Tracker.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseTrackerBean {
	public enum Category {Food, Travel, Rent, Laundary, WaterSupply, Gym, Driving, Swimming};
    int id;
	int amount;
	String description;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="MM-dd-yyyy")
	Date dateOfExpense;
	Category category;
	
	
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateOfExpense() {
		return dateOfExpense;
	}


	public void setDateOfExpense(Date dateOfExpense) {
		this.dateOfExpense = dateOfExpense;
	}


	@Override
	public String toString() {
		return "ExpenseTrackerBean [id=" + id + ", amount=" + amount + ", description=" + description
				+ ", dateOfExpense=" + dateOfExpense + ", category=" + category + "]";
	}


	
	
}
