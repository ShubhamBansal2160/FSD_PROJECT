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


import com.managerApplication.model.Task;

import com.managerApplication.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskResource {

	@Autowired
	private TaskService taskService;

//	public ProjectResource(EmployeeService employeeService) {
//		this.employeeService = employeeService;
//	}

	@RequestMapping(value = "task", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}

	@RequestMapping(value = "task", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
		try {
			Task result = taskService.save(task);
			return ResponseEntity.created(new URI("/api/task/" + result.getTask_id())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Task>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "task", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Task> updateTask(@RequestBody Task task) throws URISyntaxException {
		if (task.getTask_id() == null) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}

		try {
			Task result = taskService.update(task);

			return ResponseEntity.created(new URI("/api/task/" + result.getTask_id())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/task/{task_id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<Void> deleteTask(@PathVariable Integer task_id) {
		taskService.delete(task_id);

		return ResponseEntity.ok().build();
	}
}
