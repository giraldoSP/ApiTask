package com.iasapi.todoapp.service;


import com.iasapi.todoapp.exceptions.ToDoExceptions;
import com.iasapi.todoapp.mapper.TaskInDTOToTask;
import com.iasapi.todoapp.persistence.entity.Task;
import com.iasapi.todoapp.persistence.entity.TaskStatus;
import com.iasapi.todoapp.persistence.repository.TaskRepository;
import com.iasapi.todoapp.service.dto.TaskInDto;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskInDTOToTask mapper;
    private final TaskRepository taskRepository;

    public TaskService(TaskInDTOToTask mapper, TaskRepository taskRepository) {
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    public Task createtask(TaskInDto taskInDto){

        Task task = mapper.map(taskInDto);
        return taskRepository.save(task);
    }

    public List<Task> findAll(){
       return this.taskRepository.findAll();
    }

    public List<Task> findByStatus(TaskStatus status){
        return this.taskRepository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTask(Long id){
        Optional<Task> optionalTask =  this.taskRepository.findById(id);

        if (optionalTask.isEmpty()){
            throw new ToDoExceptions(" Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    public void deleteTask(Long id){
        Optional<Task> optionalTask =  this.taskRepository.findById(id);

        if (optionalTask.isEmpty()){
            throw new ToDoExceptions(" Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}
