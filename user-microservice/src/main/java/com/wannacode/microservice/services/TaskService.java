package com.wannacode.microservice.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wannacode.microservice.model.Task;

@Service
@FeignClient(name = "TASK-MICROSERVICE")
public interface TaskService {
	
	@RequestMapping("/user/{id}/tasks")
	ResponseEntity<List<Task>> userTasks(@PathVariable("id") Long userId);
}
