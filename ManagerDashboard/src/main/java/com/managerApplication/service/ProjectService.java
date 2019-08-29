	package com.managerApplication.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managerApplication.model.Project;
import com.managerApplication.repository.ProjectRepository;


@Service
public class ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public Project save(Project project) {
		if (project.getProject_id() != null && projectRepository.exists(project.getProject_id())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return projectRepository.save(project);
	}
	
	public Project update(Project project) {
		if (project.getProject_id() != null && !projectRepository.exists(project.getProject_id())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return projectRepository.save(project);
	}

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project findOne(Integer project_id) {
		return projectRepository.findOne(project_id);
	}

	public void delete(Integer project_id) {
		projectRepository.delete(project_id);
	}
}
