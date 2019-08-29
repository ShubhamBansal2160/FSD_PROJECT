package com.managerApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managerApplication.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
