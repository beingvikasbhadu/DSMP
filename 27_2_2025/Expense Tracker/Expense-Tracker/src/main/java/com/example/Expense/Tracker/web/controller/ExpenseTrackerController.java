package com.example.Expense.Tracker.web.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expense.Tracker.bean.BudgetBean;
import com.example.Expense.Tracker.bean.ExpenseTrackerBean;
import com.example.Expense.Tracker.service.ExpenseTrackerService;

@RestController
public class ExpenseTrackerController {
   @Autowired
   ExpenseTrackerService expenseTrackerService;		
	
	// add 
	// show warning if expense exceeds the budget of this month
	@RequestMapping(value="/add",method=RequestMethod.POST)
	 public String addExpense(@RequestBody ExpenseTrackerBean expense)
	 {
		System.out.println(expense);
	   return expenseTrackerService.addExpenseDetail(expense);
	 }
	
	// delete
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String deleteExpense(@PathVariable("id") int id)
	{
		return expenseTrackerService.deleteExpenseDetail(id);
	}
	
	// view all
	
	@RequestMapping(value="/view/all",method=RequestMethod.GET)
	public List<ExpenseTrackerBean> viewAllExpenses()
	{
	 return expenseTrackerService.viewAllExpensesDetails();
	
	}
	// view category wise
	
	@RequestMapping(value="/view/category/{category}",method=RequestMethod.GET)
	public List<ExpenseTrackerBean> viewCategoryWiseExpenses(@PathVariable("category") String category)
	{
		return expenseTrackerService.viewCategoryBasedExpensesDetails(category);
	}
	
	// view summary for a month of this year
	
	@RequestMapping(value="/view/summary/{month}",method=RequestMethod.GET)
	public String viewSummaryForAMonth(@PathVariable("month") int month)
	{
		if(month>12)
			 return "Month value is not valid!";
		int total_expense=expenseTrackerService.viewSummaryForMonthOfThisYear(month);
		
		return "Total Expense "+getMonthName(month)+":"+total_expense;
	}
	
	// view summary
	
	@RequestMapping(value="/view/summary/all",method=RequestMethod.GET)
	public String viewSummary()
	{
		int total_summary= expenseTrackerService.viewSummary();
		return "Total Expenses:"+total_summary;
	}
	
	
	
	// set a budget for a upcoming month
	@RequestMapping(value="/set-budget",method=RequestMethod.POST)
	public String setBudgetForAUpcomingMonth(@RequestBody BudgetBean budgetBean)
	{
		System.out.println(budgetBean);
	   BudgetBean added_budgetBean=expenseTrackerService.setABugetForUpcomingMonth(budgetBean);
	   if(added_budgetBean==null)
		    return "Unable to add budget";
	   else
		   return "budget added succesfully!";
	   
	}
	
	// allow user to export to a csv file
	
	@RequestMapping(value="/export",method=RequestMethod.GET)
	public String exportExpensesToCsv()
	{
		 List<ExpenseTrackerBean> expenses=expenseTrackerService.viewAllExpensesDetails();

		        String filePath = "expenses.csv";

		        try (FileWriter writer = new FileWriter(filePath)) {
		            // Write CSV header
		            writer.append("ID,Amount,Description,Date,Category\n");

		            // Write data rows
		            for (ExpenseTrackerBean expense : expenses) {
		                writer.append(expense.getId() + ",")
		                      .append(expense.getAmount() + ",")
		                      .append(expense.getDescription() + ",")
		                      .append(expense.getDateOfExpense() + ",")
		                      .append(expense.getCategory().name() + "\n");
		            }

		            return "CSV file created successfully!";
		        } catch (IOException e) {
		          
		            return "Can't export csv file";
		        }
	}
	
	String getMonthName(int monthNumber)
	{

    // Create Calendar instance
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, monthNumber - 1); // Month is zero-based (0 = January)

    // Convert Calendar to Date
    Date date = calendar.getTime();

    // Format Date to get Month Name
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM"); // "MMMM" gives full month name
    String monthName = sdf.format(date);
    return monthName;
	}
	 
	
}
