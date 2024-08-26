package org.jsp.se.controller;

import java.util.List;

import org.jsp.se.entity.Task;
import org.jsp.se.responsestructure.ResponseStructure;
import org.jsp.se.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@CrossOrigin(origins = "*")
@RestController
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	
	
	@Operation(description =  "We have to pass Task Entity as RequestBody and Passes Entity will we persisted into DB table", summary = "To Create Task Entity")
	@ApiResponses(value = {@ApiResponse(description = "Task Saved Successfully...", responseCode = "200"),@ApiResponse(description = "Unable to Save Task Becouse of Duplicate Email",responseCode = "400"),@ApiResponse(description = "Unable to Save Task Becouse of Duplicate Phone Number", responseCode = "400")})
	@PostMapping("/tasks")
	public ResponseEntity<ResponseStructure<Task>> saveTask(@RequestBody Task task){
		return service.saveTask(task);
	}
	
	@PatchMapping("/tasks/completed/{id}")
	public ResponseEntity<ResponseStructure<Task>> setStatusToCompleted(@PathVariable int id){
		return service.setStatusToCompleted(id);
	}
	
	@PatchMapping("/tasks/pending/{id}")
	public ResponseEntity<ResponseStructure<Task>> setStatusToPending(@PathVariable int id){
		return service.setStatusToPending(id);
	}
	
	@PatchMapping("/tasks/deleted/{id}")
	public ResponseEntity<ResponseStructure<Task>> setStatusToDeleted(@PathVariable int id){
		return service.setStatusToDeleted(id);
	}
	
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<ResponseStructure<Task>> findTaskById(@PathVariable int id){
		return service.findTaskById(id);
	}
	@Operation(summary = "To Fetch All The Task",description = "This API Will fetch All the Task Whose Status is Not DELETED")
	@ApiResponses({@ApiResponse(responseCode = "200",description = "Fetched All Task's Successfully..."),@ApiResponse(responseCode = "400",description = "Unable To Fetch The task...")})
	@GetMapping("/tasks")
	public ResponseEntity<ResponseStructure<List<Task>>> findAlltasks(){
		return service.findAllTasks();
	}
	
	@Operation(summary = "To Delete The Task",description = "This API Will  set the Status of The task to DELETED ")
	@ApiResponses({@ApiResponse(responseCode = "200", description = "Task Deleted Successfully..."),@ApiResponse(responseCode = "400",description = "Invalid Task Id Unable To Delete")})
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteTaskById(@PathVariable int id){
		return service.deleteTaskById(id);
	}
	
	@GetMapping("/tasks/pending")
	public ResponseEntity<ResponseStructure<List<Task>>> findAllPendingTasks(){
		return service.findAllPendingTasks();
	}
	
	@GetMapping("/tasks/completed")
	public ResponseEntity<ResponseStructure<List<Task>>> findAllCompletedTasks(){
		return service.findAllCompletedtasks();
	}
 	
}
