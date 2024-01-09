package com.taskmanagement.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.dto.request.TaskRequest;
import com.taskmanagement.model.TaskDetails;
import com.taskmanagement.service.TaskManagementService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskManagementController {
	@Autowired
	private TaskManagementService taskManagementService;

	@GetMapping("/tasks")
	public <T> T getAllTasks(Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return taskManagementService.getAllTasks(username);
	}

	@PostMapping("/task")
	public <T> T addTask(@RequestBody TaskDetails taskDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return taskManagementService.addTask(username, taskDetails);
	}

	@PutMapping("/task")
	public <T> T updateTask(@RequestBody TaskRequest taskDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return taskManagementService.updateTask(username, taskDetails);
	}

	@DeleteMapping("/task")
	public <T> T deleteTask(@RequestParam String taskId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return taskManagementService.deleteTask(username, taskId);
	}
}