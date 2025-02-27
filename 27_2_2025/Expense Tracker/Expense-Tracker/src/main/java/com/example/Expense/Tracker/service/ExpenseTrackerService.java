package com.example.Expense.Tracker.service;

import java.util.List;

import com.example.Expense.Tracker.bean.BudgetBean;
import com.example.Expense.Tracker.bean.ExpenseTrackerBean;

public interface ExpenseTrackerService {
       public String addExpenseDetail(ExpenseTrackerBean expense);
       public String deleteExpenseDetail(int id);
       public List<ExpenseTrackerBean> viewAllExpensesDetails();
       public List<ExpenseTrackerBean> viewCategoryBasedExpensesDetails(String category);
       public int viewSummaryForMonthOfThisYear(int month);
       public BudgetBean setABugetForUpcomingMonth(BudgetBean budget);
       public int viewSummary();

}
