package org.jsp.se.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jsp.se.dao.TaskDao;
import org.jsp.se.entity.Task;
import org.jsp.se.responsestructure.ResponseStructure;
import org.jsp.se.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskDao dao;

	public ResponseEntity<ResponseStructure<Task>> saveTask(Task task) {
		task.setStatus(Status.CREATED);
		ResponseStructure<Task> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.CREATED.value());
		structure.setMessage("Task Created Successfully...");
		structure.setBody(dao.saveTask(task));
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Task>>> findAllTasks() {
		List<Task> list = dao.findAllTasks();
		ArrayList<Task> al = new ArrayList<>();
		for (Task t : list)
			if (!t.getStatus().toString().equalsIgnoreCase("DELETED"))
				al.add(t);
		ResponseStructure<List<Task>> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("All Tasks Found Successfully...");
		structure.setBody(al);
		return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Task>> findTaskById(int id) {
		Optional<Task> optional = dao.findTaskById(id);
//		if(optional.isEmpty())
//			throw new InvalidtaskIdException();
		ResponseStructure<Task> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("Task Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteTaskById(int id) {
		Optional<Task> optinal = dao.findTaskById(id);
//		if(optinal.isEmpty())
//			throw new InvalidTaskIdException();
		Task task = optinal.get();
		task.setStatus(Status.DELETED);
		Task dbTask = dao.updateTask(task);
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("Task Deleted Successfully...");
		structure.setBody("Task Deleted...");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Task>>> findAllPendingTasks() {
		List<Task> list = dao.findAllTasks();
		ArrayList<Task> al = new ArrayList<>();
		for (Task t : list)
			if (t.getStatus().toString().equalsIgnoreCase("PENDING"))
				al.add(t);
		ResponseStructure<List<Task>> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("All PENDING Task Found Successfully...");
		structure.setBody(al);
		return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Task>>> findAllCompletedtasks() {
		List<Task> list = dao.findAllTasks();
		ArrayList<Task> al = new ArrayList<>();
		for (Task t : list)
			if (t.getStatus().toString().equalsIgnoreCase("COMPLETED"))
				al.add(t);
		ResponseStructure<List<Task>> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("All COMPLETED Task Found Successfully...");
		structure.setBody(al);
		return new ResponseEntity<ResponseStructure<List<Task>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Task>> setStatusToCompleted(int id) {
		Optional<Task> optinal = dao.findTaskById(id);
//		if(optinal.isEmpty())
//		throw new InvalidTaskIdException();
		Task task = optinal.get();
		task.setStatus(Status.COMPLETED);
		Task dbTask = dao.updateTask(task);
		ResponseStructure<Task> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("Status Updated as COMPLETED Successfully...");
		structure.setBody(dbTask);
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Task>> setStatusToPending(int id) {
		Optional<Task> optinal = dao.findTaskById(id);
//		if(optinal.isEmpty())
//		throw new InvalidTaskIdException();
		Task task = optinal.get();
		task.setStatus(Status.PENDING);
		Task dbTask = dao.updateTask(task);
		ResponseStructure<Task> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("Status Updated as PENDING Successfully...");
		structure.setBody(dbTask);
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Task>> setStatusToDeleted(int id) {
		Optional<Task> optinal = dao.findTaskById(id);
//		if(optinal.isEmpty())
//		throw new InvalidTaskIdException();
		Task task = optinal.get();
		task.setStatus(Status.DELETED);
		Task dbTask = dao.updateTask(task);
		ResponseStructure<Task> structure = new ResponseStructure<>();
		structure.setHttpCode(HttpStatus.OK.value());
		structure.setMessage("Status Updated as DELETED Successfully...");
		structure.setBody(dbTask);
		return new ResponseEntity<ResponseStructure<Task>>(structure, HttpStatus.OK);
	}

}
