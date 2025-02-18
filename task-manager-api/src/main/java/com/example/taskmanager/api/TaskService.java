package com.example.taskmanager.api;

import java.util.List;

/**
 * Interfaccia che definisce i metodi per la gestione delle attività (Tasks).
 */
public interface TaskService {
    List<Task> getAllTasks(); // Recupera tutte le attività

    Task getTask(Long id); // Recupera una specifica attività per ID

    void addTask(Task task); // Aggiunge una nuova attività

    void updateTask(Task task); // Aggiorna un'attività esistente

    void deleteTask(Long id); // Elimina un'attività per ID
}
