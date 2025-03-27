package com.BlueAlastor.task.service;

import com.BlueAlastor.task.dto.DTOTask;
import com.BlueAlastor.task.entity.EntityTask;
import com.BlueAlastor.task.exception.ExceptionElementNotFound;
import com.BlueAlastor.task.exception.ExceptionErrorMessage;
import com.BlueAlastor.task.repository.RepositoryTask;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.BlueAlastor.task.exception.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.BlueAlastor.task.exception.ErrorCode.NOT_FOUND;

@RequiredArgsConstructor
public class ServiceTask {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ServiceTask.class);

    private final RepositoryTask taskRepository;
    private final ModelMapper modelMapper;


    public List<DTOTask> getAllTasks() {
        logger.info("Recupero tutte le task");
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, DTOTask.class))
                .collect(Collectors.toList());
    }

    public DTOTask getTaskById(Long id) {
        logger.info("Recupero task con id {} "+  id);
        EntityTask task = taskRepository.findById(id)
                .orElseThrow(() -> new ExceptionElementNotFound("Task non trovata con ID " + id, NOT_FOUND));
        return modelMapper.map(task, DTOTask.class);
    }

    public DTOTask createTask(DTOTask taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new ExceptionErrorMessage("Il titolo della task non può essere vuoto", INTERNAL_SERVER_ERROR);
        }

        logger.info("Creazione task: {} " + taskDTO.getTitle());
        EntityTask task = modelMapper.map(taskDTO, EntityTask.class);
        EntityTask savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, DTOTask.class);
    }
}