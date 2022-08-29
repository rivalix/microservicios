package com.wannacode.microservice.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wannacode.microservice.model.Category;
import com.wannacode.microservice.model.Task;
import com.wannacode.microservice.repository.CategoryRepository;
import com.wannacode.microservice.repository.TaskRepository;

@RestController
@RequestMapping("task-service")
public class TaskController {
	Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	private TaskRepository taskRepository;
	private CategoryRepository categoryRepository;
	
	public TaskController(TaskRepository taskRepository, CategoryRepository categoryRepository) {
		this.taskRepository = taskRepository;
		this.categoryRepository = categoryRepository;
	}
	
	@PostMapping("task")
	@Transactional
	public ResponseEntity<Task> addTasks(@RequestBody Task task){
		logger.info("Task {}" ,task);
		Set<Category> categories=task.getCategories();
		Set<Category> taskCategories= new HashSet<>();
		
		categories.stream().forEach(category->{
			Category existingCategory= categoryRepository.findByName(category.getName());
			if(existingCategory == null) {
				existingCategory = categoryRepository.save(category);
				taskCategories.add(existingCategory);
			}
			existingCategory.setTasks(new HashSet<>());
			taskCategories.add(existingCategory);
		});
		task.setCategories(taskCategories);
		Task savedTask= taskRepository.save(task);
		return new ResponseEntity<Task>(savedTask,HttpStatus.CREATED);
	}
	
	@GetMapping("user/{id}/tasks")
	public List<Task> userTasks(@PathVariable("id") Long userId) {
		logger.info("Task for user id {}" ,userId);
		return taskRepository.findByUserId(userId);
	}
}
