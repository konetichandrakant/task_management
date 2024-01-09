package com.taskmanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanagement.dto.request.TaskRequest;
import com.taskmanagement.model.TaskDetails;
import com.taskmanagement.model.User;
import com.taskmanagement.repository.TaskDetailsRepo;
import com.taskmanagement.repository.UserRepo;

@Service
public class TaskManagementService {
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private TaskDetailsRepo taskDetailsRepository;

	private UUID uuid;

	@SuppressWarnings("unchecked")
	public <T> T getAllTasks(String email) {
		User user = userRepository.findByEmail(email).get();
		if (user == null)
			return (T) "user does not exist";
		return (T) user.getTasks();
	}

	@SuppressWarnings("unchecked")
	public <T> T addTask(String email, TaskDetails details) {
		User user = userRepository.findByEmail(email).get();
		if (user == null)
			return (T) "user does not exist";
		details.setId(uuid.toString());
		List<TaskDetails> tasks = user.getTasks();
		tasks.add(details);
		user.setTasks(tasks);
		taskDetailsRepository.save(details);
		userRepository.save(user);
		return (T) "Added successfuly!!!";
	}

	@SuppressWarnings("unchecked")
	public <T> T updateTask(String taskId, TaskRequest taskRequest) {
		TaskDetails taskDetails = taskDetailsRepository.findById(taskId).get();
		if (taskDetails == null)
			return (T) "task does not exist";
		taskDetails.setCompleted(taskRequest.isCompleted());
		taskDetails.setHeading(taskRequest.getHeading());
		taskDetails.setNote(taskRequest.getNote());
		taskDetailsRepository.save(taskDetails);
		return (T) "Updated successfuly!!!";
	}

	@SuppressWarnings("unchecked")
	public <T> T deleteTask(String email, String taskId) {
		User user = userRepository.findByEmail(email).get();
		if (user == null)
			return (T) "user does not exist";
		TaskDetails taskDetails = taskDetailsRepository.findById(taskId).get();
		if (taskDetails == null)
			return (T) "task does not exist";
		List<TaskDetails> tasks = user.getTasks();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getId().equals(taskId)) {
				tasks.remove(i);
				break;
			}
		}
		taskDetailsRepository.deleteById(taskId);
		return (T) "Deleted successfuly!!!";
	}
}
