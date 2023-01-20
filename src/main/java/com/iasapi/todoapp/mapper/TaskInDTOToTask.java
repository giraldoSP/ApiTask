package com.iasapi.todoapp.mapper;

import com.iasapi.todoapp.persistence.entity.Task;
import com.iasapi.todoapp.persistence.entity.TaskStatus;
import com.iasapi.todoapp.service.dto.TaskInDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements iMapper<TaskInDto, Task>{

    @Override
    public Task map(TaskInDto in) {

        Task task = new Task();

        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);

        return task;
    }
}
