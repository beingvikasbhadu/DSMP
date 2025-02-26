package com.example.Task.Tracker.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskTrackerBean {
       int id;
       String description;
       String status;
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mma")
       Date createdAt;
       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mma")
       Date updatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "TaskTrackerBean [id=" + id + ", description=" + description + ", status=" + status + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
       
}
