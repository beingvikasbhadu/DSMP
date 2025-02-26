package com.example.Task.Tracker.dao;

import java.util.List;

import com.example.Task.Tracker.bean.TaskTrackerBean;

public interface TaskTrackerDAO {
   TaskTrackerBean  saveTaskToFile(TaskTrackerBean task);
   TaskTrackerBean updateTaskToFile(TaskTrackerBean task, int id);
   TaskTrackerBean deleteTaskFromFile(int id);
   List<TaskTrackerBean> readTasksFromFile();
}
