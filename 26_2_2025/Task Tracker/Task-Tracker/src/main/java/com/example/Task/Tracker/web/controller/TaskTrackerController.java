package com.example.Task.Tracker.web.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Tracker.bean.TaskTrackerBean;
import com.example.Task.Tracker.service.TaskTrackerService;

@RestController
public class TaskTrackerController {
	
   @Autowired
   TaskTrackerService taskTrackerService;
	// To add a task
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public TaskTrackerBean addTask(@RequestBody TaskTrackerBean bean) {
		
	return 	taskTrackerService.addTaskDetail(bean);
	}

	// To view all tasks
	@RequestMapping(value = "/show/all", method = RequestMethod.GET)
	public List<TaskTrackerBean> showAllTasks() {
		return taskTrackerService.showAllTasksDetails();
	}

	// to view only in-progress tasks
	@RequestMapping(value = "/show/in-progress", method = RequestMethod.GET)
	public List<TaskTrackerBean> showInProgressTasks() {
		return taskTrackerService.showInProgressTasksDetails();
	}

	// to view only to-do tasks
	@RequestMapping(value = "/show/to-do", method = RequestMethod.GET)
	public List<TaskTrackerBean> showToDoTasks() {
		return taskTrackerService.showToDoTasksDetails();
	}

	// to view only done tasks
	@RequestMapping(value = "/show/done", method = RequestMethod.GET)
	public List<TaskTrackerBean> showDoneTasks() {
		return taskTrackerService.showDoneTasksDetails();
	}

	// update a task
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public TaskTrackerBean updateTask(@PathVariable("id") int id, @RequestBody TaskTrackerBean new_bean) {

		 return taskTrackerService.updateTaskDetail(id, new_bean);

	}

	// delete a task
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public TaskTrackerBean deleteTask(@PathVariable("id") int id) {
		return taskTrackerService.deleteTaskDetail(id);
	}

	

}
