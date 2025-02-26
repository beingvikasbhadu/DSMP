package com.example.Task.Tracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Tracker.bean.TaskTrackerBean;
import com.example.Task.Tracker.dao.TaskTrackerDAO;

@Service
public class TaskTrackerServiceImpl implements TaskTrackerService {
   @Autowired
   TaskTrackerDAO taskTrackerDAO;
   
	@Override
	public TaskTrackerBean addTaskDetail(TaskTrackerBean bean) {
		bean.setStatus("to-do");
		bean.setCreatedAt(new Date()); // pattern should be "yyy-MM-dd"
		bean.setUpdatedAt(new Date());
	return 	taskTrackerDAO.saveTaskToFile(bean);
	}

	@Override
	public List<TaskTrackerBean> showAllTasksDetails() {
		List<TaskTrackerBean> tasks = taskTrackerDAO.readTasksFromFile();
		return tasks;
	}

	@Override
	public List<TaskTrackerBean> showInProgressTasksDetails() {
		List<TaskTrackerBean> all_tasks = taskTrackerDAO.readTasksFromFile();
		List<TaskTrackerBean> inProgress_tasks = new ArrayList<>();

		for (int i = 0; i < all_tasks.size(); i++) {
			if (all_tasks.get(i).getStatus().equals("in-progress"))
				inProgress_tasks.add(all_tasks.get(i));
		}
		return inProgress_tasks;
	}

	@Override
	public List<TaskTrackerBean> showToDoTasksDetails() {
		List<TaskTrackerBean> all_tasks = taskTrackerDAO.readTasksFromFile();
		List<TaskTrackerBean> toDo_tasks = new ArrayList<>();

		for (int i = 0; i < all_tasks.size(); i++) {
			if (all_tasks.get(i).getStatus().equals("to-do"))
				toDo_tasks.add(all_tasks.get(i));
		}
		return toDo_tasks;
	}

	@Override
	public List<TaskTrackerBean> showDoneTasksDetails() {
		List<TaskTrackerBean> all_tasks = taskTrackerDAO.readTasksFromFile();
		List<TaskTrackerBean> done_tasks = new ArrayList<>();

		for (int i = 0; i < all_tasks.size(); i++) {
			if (all_tasks.get(i).getStatus().equals("done"))
				done_tasks.add(all_tasks.get(i));
		}
		return done_tasks;
	}

	@Override
	public TaskTrackerBean updateTaskDetail(int id, TaskTrackerBean new_bean) {
		return taskTrackerDAO.updateTaskToFile(new_bean, id);
	}

	@Override
	public TaskTrackerBean deleteTaskDetail(int id) {
		return taskTrackerDAO.deleteTaskFromFile(id);
	}

}
