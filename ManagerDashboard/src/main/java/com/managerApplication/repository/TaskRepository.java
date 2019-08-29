package com.managerApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managerApplication.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
