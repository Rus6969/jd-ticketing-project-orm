package com.cybertek.repository;

import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {


    List<Task>findAllByInsertUserId(String description);
}