package com.managerApplication.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.managerApplication.model.Project;
import com.managerApplication.service.ProjectService;

@RestController
@RequestMapping("/api")
public class ProjectResource {

	@Autowired
	private ProjectService projectService;

//	public ProjectResource(EmployeeService employeeService) {
//		this.employeeService = employeeService;
//	}

	@RequestMapping(value = "project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getAllProjects() {
		return projectService.findAll();
	}

	@RequestMapping(value = "project", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Project> createProject(@RequestBody Project project) throws URISyntaxException {
		try {
			Project result = projectService.save(project);
			return ResponseEntity.created(new URI("/api/project/" + result.getProject_id())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Project>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "project", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Project> updateProject(@RequestBody Project project) throws URISyntaxException {
		if (project.getProject_id() == null) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}

		try {
			Project result = projectService.update(project);

			return ResponseEntity.created(new URI("/api/project/" + result.getProject_id())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/project/{project_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ResponseEntity<Void> deleteProject(@PathVariable Integer project_id) {
		projectService.delete(project_id);

		return ResponseEntity.ok().build();
	}
}
