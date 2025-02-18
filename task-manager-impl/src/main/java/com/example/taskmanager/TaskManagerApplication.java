package com.example.taskmanager;

import org.apache.camel.component.servlet.springboot.ServletMappingAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale che avvia l'applicazione Spring Boot.
 */
@SpringBootApplication(exclude = {ServletMappingAutoConfiguration.class})
public class TaskManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}
