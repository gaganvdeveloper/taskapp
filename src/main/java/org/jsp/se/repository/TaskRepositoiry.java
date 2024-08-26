package org.jsp.se.repository;

import org.jsp.se.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoiry extends JpaRepository<Task, Integer>{

}
