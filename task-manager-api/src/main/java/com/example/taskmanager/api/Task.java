package com.example.taskmanager.api;

/**
 * Classe rappresentante un'attività (Task).
 */
public class Task {
    private Long id;
    private String title;
    private String description;
    private String status;

    // Costruttore vuoto richiesto da framework come Spring e Jackson
    public Task() {
    }

    // Costruttore con parametri per inizializzare una nuova Task
    public Task(Long id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Metodi getter e setter per accedere e modificare i campi privati
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Metodo toString per ottenere una rappresentazione testuale dell'oggetto
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
