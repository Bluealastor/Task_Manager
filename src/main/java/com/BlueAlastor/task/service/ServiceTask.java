package com.BlueAlastor.task.service;

import com.BlueAlastor.task.dto.DTOTask;
import com.BlueAlastor.task.entity.EntityTask;
import com.BlueAlastor.task.exception.ExceptionElementNotFound;
import com.BlueAlastor.task.exception.ExceptionErrorMessage;
import com.BlueAlastor.task.repository.RepositoryTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.BlueAlastor.task.exception.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.BlueAlastor.task.exception.ErrorCode.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceTask {

    private final RepositoryTask taskRepository;
    private final ModelMapper modelMapper;


    public List<DTOTask> getAllTasks() {
        log.info("Recupero tutte le task");
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, DTOTask.class))
                .collect(Collectors.toList());
    }

    public DTOTask getTaskById(Long id) {
        log.info("Recupero task con id {} "+  id);
        EntityTask task = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionElementNotFound("Task non trovata con ID " + id, NOT_FOUND));
        return modelMapper.map(task, DTOTask.class);
    }

    public DTOTask createTask(DTOTask taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new ExceptionErrorMessage("Il titolo della task non può essere vuoto", INTERNAL_SERVER_ERROR);
        }

        log.info("Creazione task: {} " + taskDTO.getTitle());
        EntityTask task = modelMapper.map(taskDTO, EntityTask.class);
        EntityTask savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, DTOTask.class);
    }

    public DTOTask updateTask(Long id, DTOTask taskDTO) {
        EntityTask existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setCompleted(taskDTO.isCompleted());
        EntityTask updatedTask = taskRepository.save(existingTask);
        return modelMapper.map(updatedTask, DTOTask.class);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}