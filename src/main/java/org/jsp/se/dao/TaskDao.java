package org.jsp.se.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.se.entity.Task;
import org.jsp.se.repository.TaskRepositoiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDao {
	
	@Autowired
	private TaskRepositoiry repositoiry;
	
	
	public Task saveTask(Task task) {
		return repositoiry.save(task);
	}
	
	public Task updateTask(Task task) {
		return repositoiry.save(task);
	}
	
	public Optional<Task> findTaskById(int id){
		return repositoiry.findById(id);
	}
	
	public List<Task> findAllTasks(){
		return repositoiry.findAll();
	}
	
	
	
}
