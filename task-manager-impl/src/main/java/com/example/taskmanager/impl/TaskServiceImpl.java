package com.example.taskmanager.impl;

import com.example.taskmanager.api.Task;
import com.example.taskmanager.api.TaskService;
import org.osgi.service.component.annotations.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Implementazione del servizio TaskService che gestisce le attività in memoria.
 */
@Service
@Component(service = TaskService.class, immediate = true)
public class TaskServiceImpl implements TaskService {

    // Mappa per memorizzare le attività (simulazione di un database)
    private final ConcurrentHashMap<Long, Task> tasks = new ConcurrentHashMap<>();

    // Generatore automatico di ID univoci
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public Task getTask(Long id) {
        return tasks.get(id);
    }

    @Override
    public void addTask(Task task) {
        if (task.getId() == null) {
            task.setId(idGenerator.incrementAndGet());
        }
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateTask(Task task) {
        if (task.getId() != null && tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void deleteTask(Long id) {
        tasks.remove(id);
    }
}
