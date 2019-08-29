package com.managerApplication.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.sun.istack.internal.NotNull;

@Entity
@Table(name="Project")
@EntityListeners(AuditingEntityListener.class)
public class Project {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer project_id;
	
	@NotNull
	@NotEmpty
	private String project;

	private Date start_Date;

	private Date end_Date;
	
	private int priority;
	

	public Project() {
	}

	public Integer getProject_id() {
		return project_id;
	}

	public void setProject_id(Integer project_id) {
		this.project_id = project_id;
	}

	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}


	public Date getStart_Date() {
		return start_Date;
	}


	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}


	public Date getEnd_Date() {
		return end_Date;
	}


	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}

}
