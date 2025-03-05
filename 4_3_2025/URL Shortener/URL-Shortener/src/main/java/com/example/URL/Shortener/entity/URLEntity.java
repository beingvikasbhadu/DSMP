package com.example.URL.Shortener.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "url")
public class URLEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String url;
	String shortCode;
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedAt;
	
	Integer accessCount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
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

	public Integer getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(Integer accessCount) {
		this.accessCount = accessCount;
	}

	@Override
	public String toString() {
		return "URLEntity [id=" + id + ", url=" + url + ", shortCode=" + shortCode + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", accessCount=" + accessCount + "]";
	}

}
