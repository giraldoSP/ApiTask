package com.iasapi.todoapp.controller;

import com.iasapi.todoapp.persistence.entity.Task;
import com.iasapi.todoapp.persistence.entity.TaskStatus;
import com.iasapi.todoapp.service.TaskService;
import com.iasapi.todoapp.service.dto.TaskInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    final private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask (@RequestBody TaskInDto taskInDto){
       return  this.taskService.createtask(taskInDto);
    }

    @GetMapping
    public List<Task> findAllTask(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findTaskById(@PathVariable("status") TaskStatus status){
        return this.taskService.findByStatus(status);
    }

    @PatchMapping("/updatefinished/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable("id") Long id){
        this.taskService.updateTask(id);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id){
        this.taskService.deleteTask(id);
        return  ResponseEntity.noContent().build();
    }


}
