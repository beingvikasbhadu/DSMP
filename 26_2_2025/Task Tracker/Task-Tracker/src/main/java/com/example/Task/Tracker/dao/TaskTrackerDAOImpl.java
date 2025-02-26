package com.example.Task.Tracker.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Task.Tracker.bean.TaskTrackerBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Repository
public class TaskTrackerDAOImpl implements TaskTrackerDAO {
	private static final String File_Path = "tasks.json";
	
	public TaskTrackerBean saveTaskToFile(TaskTrackerBean task) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		File file = new File(File_Path);
		List<TaskTrackerBean> tasks = new ArrayList<>();
		int next_id = 0;

		// Check if the file exists
		if (file.exists()) {

			// Read existing tasks from the file
			tasks = readTasksFromFile();
			next_id = tasks.get(tasks.size() - 1).getId() + 1;

		}

		// Append the new task
		task.setId(next_id);
		tasks.add(task);

		// Write the updated list back to file
		try (FileWriter writer = new FileWriter(file, false)) { // `false` overwrites the file
			objectMapper.writeValue(writer, tasks);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return task;
	}

	
	public TaskTrackerBean updateTaskToFile(TaskTrackerBean new_task, int id) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		TaskTrackerBean updated_task = null;
		File file = new File(File_Path);
		List<TaskTrackerBean> tasks = new ArrayList<>();

		// Check if the file exists
		if (file.exists()) {

			// Read existing tasks from the file
			tasks = readTasksFromFile();
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getId() == id) {

					// to update task heading
					if (new_task.getDescription() != null) {
						tasks.get(i).setDescription(new_task.getDescription());
					}
					// to update status
					else {
						tasks.get(i).setStatus(new_task.getStatus());
					}
					tasks.get(i).setUpdatedAt(new Date());
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

	
	public TaskTrackerBean deleteTaskFromFile(int id) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty-print JSON
		TaskTrackerBean deleted_bean = null;
		File file = new File(File_Path);
		List<TaskTrackerBean> tasks = new ArrayList<>();

		// Check if the file exists
		if (file.exists()) {
			tasks = readTasksFromFile();

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

	
	public List<TaskTrackerBean> readTasksFromFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Read JSON file and map it to List<TaskTrackerBean>
			return objectMapper.readValue(new File(File_Path), new TypeReference<List<TaskTrackerBean>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null; // Return null or handle error gracefully
		}
	}
	

}
