package com.example.MarkdownApp.bean;

public class NoteBean{
	String title;
	String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Note [title=" + title + ", description=" + description + "]";
	}

}
