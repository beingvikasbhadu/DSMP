package com.example.Expense.Tracker.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BudgetBean {
	int id;
    int budget;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM-yyyy")
    Date monthAndYear;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public Date getMonthAndYear() {
		return monthAndYear;
	}
	public void setMonthAndYear(Date monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	@Override
	public String toString() {
		return "BudgetBean [id=" + id + ", budget=" + budget + ", monthAndYear=" + monthAndYear + "]";
	}
    
    
}
