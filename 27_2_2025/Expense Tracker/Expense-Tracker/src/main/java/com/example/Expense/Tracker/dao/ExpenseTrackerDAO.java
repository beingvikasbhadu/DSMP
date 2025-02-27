package com.example.Expense.Tracker.dao;

import java.util.List;

import com.example.Expense.Tracker.bean.BudgetBean;
import com.example.Expense.Tracker.bean.ExpenseTrackerBean;

public interface ExpenseTrackerDAO {
    public ExpenseTrackerBean writeExpeseToFile(ExpenseTrackerBean expense);
    public List<ExpenseTrackerBean> readExpensesFromFile();
    public BudgetBean writeBudgetToFile(BudgetBean budget);
    public List<BudgetBean> readBudgetFromFile();
    public ExpenseTrackerBean deleteExpenseFromFile(int id);
    public BudgetBean updateBudgetToFile(BudgetBean new_budget, int id);
}
