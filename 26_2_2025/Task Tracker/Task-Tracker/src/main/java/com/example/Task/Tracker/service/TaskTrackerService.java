package com.example.Task.Tracker.service;

import java.util.List;



import com.example.Task.Tracker.bean.TaskTrackerBean;

public interface TaskTrackerService {
   public TaskTrackerBean addTaskDetail(TaskTrackerBean task);
   public List<TaskTrackerBean> showAllTasksDetails();
   public List<TaskTrackerBean> showInProgressTasksDetails();
   public List<TaskTrackerBean> showToDoTasksDetails();
   public List<TaskTrackerBean> showDoneTasksDetails();
   public TaskTrackerBean updateTaskDetail(int id,TaskTrackerBean new_bean);
   public TaskTrackerBean deleteTaskDetail( int id);
   
   
}
