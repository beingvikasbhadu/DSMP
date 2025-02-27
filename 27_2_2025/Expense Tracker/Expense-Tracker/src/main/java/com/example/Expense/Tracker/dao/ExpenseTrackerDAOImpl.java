package com.example.Expense.Tracker.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Expense.Tracker.bean.BudgetBean;
import com.example.Expense.Tracker.bean.ExpenseTrackerBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Repository
public class ExpenseTrackerDAOImpl implements ExpenseTrackerDAO {
    public static final String Expense_File_Path="expense.json";
    public static final String Budget_File_Path="Budget.json";
    
	@Override
	public ExpenseTrackerBean writeExpeseToFile(ExpenseTrackerBean expense) {
		System.out.println("EXpense:"+expense);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		File file = new File(Expense_File_Path);
		List<ExpenseTrackerBean> tasks = new ArrayList<>();
		int next_id = 0;

		// Check if the file exists
		if (file.exists()) {

			// Read existing tasks from the file
			tasks = readExpensesFromFile();
			if(tasks!=null && !tasks.isEmpty())
			next_id = tasks.get(tasks.size() - 1).getId() + 1;

		}

		// Append the new task
		expense.setId(next_id);
		tasks.add(expense);

		// Write the updated list back to file
		try (FileWriter writer = new FileWriter(file, false)) { // `false` overwrites the file
			objectMapper.writeValue(writer, tasks);
			return expense;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ExpenseTrackerBean> readExpensesFromFile() {
		File file = new File(Expense_File_Path);
	    ObjectMapper objectMapper = new ObjectMapper();

	    // Check if the file exists and is not empty
	    if (!file.exists() || file.length() == 0) {
	        return new ArrayList<>(); // Return empty list instead of null
	    }
		
		try {
			// Read JSON file and map it to List<TaskTrackerBean>
			return objectMapper.readValue(new File(Expense_File_Path), new TypeReference<List<ExpenseTrackerBean>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null; // Retrn null or handle error gracefully
		}
	}

	@Override
	public BudgetBean writeBudgetToFile(BudgetBean budget) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		File file = new File(Budget_File_Path);
		List<BudgetBean> tasks = new ArrayList<>();
		int next_id = 0;

		// Check if the file exists
		if (file.exists()) {

			// Read existing tasks from the file
			tasks = readBudgetFromFile();
			if(tasks!=null&&!tasks.isEmpty())
			next_id = tasks.get(tasks.size() - 1).getId() + 1;

		}

		// Append the new task
		budget.setId(next_id);
		tasks.add(budget);

		// Write the updated list back to file
		try (FileWriter writer = new FileWriter(file, false)) { // `false` overwrites the file
			objectMapper.writeValue(writer, tasks);
			return budget;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<BudgetBean> readBudgetFromFile() {
		
		File file = new File(Budget_File_Path);
	    ObjectMapper objectMapper = new ObjectMapper();

	    // Check if the file exists and is not empty
	    if (!file.exists() || file.length() == 0) {
	        return new ArrayList<>(); // Return empty list instead of null
	    }
		try {
			// Read JSON file and map it to List<TaskTrackerBean>
			return objectMapper.readValue(new File(Budget_File_Path), new TypeReference<List<BudgetBean>>() {
			});
		} catch (IOException e) {
			
			return null; // Return null or handle error gracefully
		}
	}
	
	public ExpenseTrackerBean deleteExpenseFromFile(int id) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		ExpenseTrackerBean deleted_bean = null;
		File file = new File(Expense_File_Path);
		List<ExpenseTrackerBean> tasks = new ArrayList<>();

		// Check if the file exists
		if (file.exists()) {
			tasks = readExpensesFromFile();

			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getId() == id) {
					deleted_bean = tasks.get(i);
					tasks.remove(i);
				}
			}

			// Write the updated list back to file
			try (FileWriter writer = new FileWriter(file, false)) { // `false` overwrites the file
				objectMapper.writeValue(writer, tasks);
			} catch (IOException e) {
				e.printStackTrace();

			}

		}

		return deleted_bean;
	}
	
	public BudgetBean updateBudgetToFile(BudgetBean new_budget, int id) {
		
		System.out.println("Updating Budget:"+new_budget);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		BudgetBean updated_task = null;
		File file = new File(Budget_File_Path);
		List<BudgetBean> tasks = new ArrayList<>();

		// Check if the file exists
		if (file.exists()) {

			// Read existing tasks from the file
			tasks = readBudgetFromFile();
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getId() == id) {

						tasks.get(i).setBudget(new_budget.getBudget());
					
					updated_task = tasks.get(i);
				}
			}

			// Write the updated list back to file
			try (FileWriter writer = new FileWriter(file, false)) { // `false` overwrites the file
				objectMapper.writeValue(writer, tasks);
			} catch (IOException e) {
				e.printStackTrace();

			}

		}

		return updated_task;
	}

}
