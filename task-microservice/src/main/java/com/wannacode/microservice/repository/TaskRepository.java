package com.wannacode.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wannacode.microservice.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByUserId(Long userId);
}
