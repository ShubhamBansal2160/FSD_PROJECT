	package com.managerApplication.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.managerApplication.model.Task;

import com.managerApplication.repository.TaskRepository;


@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public Task save(Task task) {
		if (task.getTask_id() != null && taskRepository.exists(task.getTask_id() )) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return taskRepository.save(task);
	}

	public Task update(Task task) {
		if (task.getTask_id() != null && !taskRepository.exists(task.getTask_id())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return taskRepository.save(task);
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task findOne(Integer task_id) {
		return taskRepository.findOne(task_id);
	}

	public void delete(Integer task_id) {
		taskRepository.delete(task_id);
	}
}
