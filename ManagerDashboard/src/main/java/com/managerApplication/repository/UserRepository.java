package com.managerApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.managerApplication.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
