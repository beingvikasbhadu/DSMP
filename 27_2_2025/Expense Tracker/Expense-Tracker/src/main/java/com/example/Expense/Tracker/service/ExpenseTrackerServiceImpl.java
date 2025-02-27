package com.example.Expense.Tracker.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Expense.Tracker.bean.BudgetBean;
import com.example.Expense.Tracker.bean.ExpenseTrackerBean;
import com.example.Expense.Tracker.dao.ExpenseTrackerDAO;

@Service
public class ExpenseTrackerServiceImpl implements ExpenseTrackerService {

	@Autowired
	ExpenseTrackerDAO expenseTrackerDAO;

	@Override
	public String addExpenseDetail(ExpenseTrackerBean expense) {
		// setting today's date as date of expense
		if (expense.getAmount() <= 0)
			return "Can't added less than zero expense";

		expense.setDateOfExpense(new Date());

		List<BudgetBean> listBudgetBean = expenseTrackerDAO.readBudgetFromFile();

		// extracting month of expense
		Date dateOfExpense = expense.getDateOfExpense();
		String monthOfExpense = getMonth(dateOfExpense);
		String yearOfExpense=getYear(dateOfExpense);

		ExpenseTrackerBean added_expenseTrackerBean = expenseTrackerDAO.writeExpeseToFile(expense);
		if (added_expenseTrackerBean == null)
			return "Unable to add expense";

		String response = "";

		int index = checkBudgetForAMonth(listBudgetBean, monthOfExpense,yearOfExpense);
           
		System.out.println("index:"+index);
		if (index != -1) {
			if (listBudgetBean.get(index).getBudget() < expense.getAmount())
				response += "You exceeded your budget\n";
			listBudgetBean.get(index).setBudget(listBudgetBean.get(index).getBudget() - expense.getAmount());
			BudgetBean updated_budgetBean = expenseTrackerDAO.updateBudgetToFile(listBudgetBean.get(index),
					listBudgetBean.get(index).getId());
			if (updated_budgetBean == null)
				return "Can't Update Your Budget";
		}

		response += "Expense Added Successfully!";

		return response;
	}

	@Override
	public String deleteExpenseDetail(int id) {
		List<ExpenseTrackerBean> expenseList = expenseTrackerDAO.readExpensesFromFile();

		for (int i = 0; i < expenseList.size(); i++) {
			if (expenseList.get(i).getId() == id) {
				ExpenseTrackerBean deleted_expense = expenseTrackerDAO.deleteExpenseFromFile(id);
				if (deleted_expense == null)
					return "Can't delete the Expense";
				// delete this and also increase budget for this month if exist
				List<BudgetBean> listBudgetBean = expenseTrackerDAO.readBudgetFromFile();
				Date dateOfExpense = expenseList.get(i).getDateOfExpense();
				String monthOfExpense = getMonth(dateOfExpense);
                String yearOfExpense=getYear(dateOfExpense);
				int index = checkBudgetForAMonth(listBudgetBean, monthOfExpense,yearOfExpense);

				if (index != -1) {
					listBudgetBean.get(index)
							.setBudget(listBudgetBean.get(index).getBudget() + expenseList.get(i).getAmount());
					BudgetBean updated_budgetBean = expenseTrackerDAO.updateBudgetToFile(listBudgetBean.get(index),
							listBudgetBean.get(index).getId());
					if (updated_budgetBean == null)
						return "can't update Budget!";
				}
			}
		}

		return "Expense Deleted Successfully!";
	}

	@Override
	public List<ExpenseTrackerBean> viewAllExpensesDetails() {

		return expenseTrackerDAO.readExpensesFromFile();
	}

	@Override
	public List<ExpenseTrackerBean> viewCategoryBasedExpensesDetails(String category) {
		ExpenseTrackerBean.Category enumCategory = ExpenseTrackerBean.Category.valueOf(category);

		List<ExpenseTrackerBean> list_expenses = viewAllExpensesDetails();

		List<ExpenseTrackerBean> matching_expenses = new ArrayList<>();

		for (int i = 0; i < list_expenses.size(); i++) {
			if (list_expenses.get(i).getCategory() == enumCategory)
				matching_expenses.add(list_expenses.get(i));

		}
		return matching_expenses;
	}

	@Override
	public int viewSummaryForMonthOfThisYear(int month) {
		int summary = 0;
		List<ExpenseTrackerBean> list_expenses = viewAllExpensesDetails();
		Date today_date=new Date();
		System.out.println("today's year:"+getYear(today_date));
		System.out.println("today's month:"+String.valueOf(month));
		
		for(int i=0;i<list_expenses.size();i++)
		{
			System.out.println("expenseYth:"+getYear(list_expenses.get(i).getDateOfExpense()));
			System.out.println("expenseMth:"+getMonth(list_expenses.get(i).getDateOfExpense()));
			
			if((getYear(today_date).equals(getYear(list_expenses.get(i).getDateOfExpense())))&&(getMonth(list_expenses.get(i).getDateOfExpense()).equals(String.valueOf(month))))
				{summary+=list_expenses.get(i).getAmount();
				 System.out.println("lo bhi aa liya");
				}
		}
		return summary;
	}
    
	@Override
	public int viewSummary() {
		int summary = 0;
		List<ExpenseTrackerBean> list_expenses = viewAllExpensesDetails();
		
		for(int i=0;i<list_expenses.size();i++)
		{
				summary+=list_expenses.get(i).getAmount();
		}
		return summary;
	}
	@Override
	public BudgetBean setABugetForUpcomingMonth(BudgetBean budget) {
		 Date monthAndYear=budget.getMonthAndYear();
		 Date today_date=new Date();
		 
		 Calendar cal1 = Calendar.getInstance();
		 cal1.setTime(monthAndYear);

		 Calendar cal2 = Calendar.getInstance();
		 cal2.setTime(today_date);
		 
		 if(cal2.get(Calendar.YEAR)<=cal1.get(Calendar.YEAR) && cal2.get(Calendar.MONTH)<=cal1.get(Calendar.MONTH))
		return expenseTrackerDAO.writeBudgetToFile(budget);
		 else return null;
	}

	private String getMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("M");
		String month = sdf.format(date);
		return month;
	}
  
	private String getYear(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(date);
		return year;
	}
	private int checkBudgetForAMonth(List<BudgetBean> listBudgetBean, String month, String year) {
		// 1. but before adding/deleting expense
		// 2.check is there any budget set for this month
		// 3.if exist then check if this expense exceeding the budget
		// 4. if yes then give a warning

		// 1
		
		System.out.println(year);
		
		
		System.out.println(month);
		
      try {
		for (int i = 0; i < listBudgetBean.size(); i++) {
			System.out.println(getYear(listBudgetBean.get(i).getMonthAndYear()));
			System.out.println(getMonth(listBudgetBean.get(i).getMonthAndYear()));
			if ((getYear(listBudgetBean.get(i).getMonthAndYear()).equals(year) )&& (getMonth(listBudgetBean.get(i).getMonthAndYear()).equals(month))) {
				return i;
			}
		}
      }
      catch(Exception e){
    	  System.out.println("CATCH BLOCK");
    	  return -1;
      }
      return -1;
		
	}

}
