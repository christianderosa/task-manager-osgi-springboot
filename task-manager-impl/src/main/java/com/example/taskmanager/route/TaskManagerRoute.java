package com.example.taskmanager.route;

import com.example.taskmanager.api.Task;
import com.example.taskmanager.api.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Definizione delle rotte REST con Apache Camel.
 */
@Component
public class TaskManagerRoute extends RouteBuilder {

    private final TaskService taskService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TaskManagerRoute(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .contextPath("/api")
                .port(8080)
                .bindingMode(RestBindingMode.json);

        rest("/tasks")
                .get().to("direct:getAllTasks")   // Ottieni tutte le attività
                .get("/{id}").to("direct:getTask") // Ottieni una singola attività
                .post().to("direct:addTask")      // Aggiungi una nuova attività
                .put().to("direct:updateTask")    // Aggiorna un'attività
                .delete("/{id}").to("direct:deleteTask"); // Elimina un'attività

        from("direct:getAllTasks")
                .log("Recupero tutte le task")
                .process(exchange -> exchange.getMessage().setBody(taskService.getAllTasks()));

        from("direct:getTask")
                .log("Recupero task con id: ${header.id}")
                .process(exchange -> {
                    Long id = Long.valueOf(exchange.getIn().getHeader("id", String.class));
                    Task task = taskService.getTask(id);
                    exchange.getMessage().setBody(task);
                });

        from("direct:addTask")
                .log("Raw body: ${body} (class: ${body.getClass().getName()})")
                .process(exchange -> {
                    Object body = exchange.getIn().getBody();
                    Task task;
                    if (body instanceof java.util.Map) {
                        task = objectMapper.convertValue(body, Task.class);
                    } else {
                        task = exchange.getIn().getBody(Task.class);
                    }
                    exchange.getIn().setBody(task);
                })
                .log("Task deserializzato: ${body}")
                .process(exchange -> {
                    Task task = exchange.getIn().getBody(Task.class);
                    taskService.addTask(task);
                    exchange.getMessage().setBody(task);
                });

        from("direct:updateTask")
                .log("Aggiornamento task - Raw body: ${body} (class: ${body.getClass().getName()})")
                .process(exchange -> {
                    Object body = exchange.getIn().getBody();
                    Task task;
                    if (body instanceof java.util.Map) {
                        task = objectMapper.convertValue(body, Task.class);
                    } else {
                        task = exchange.getIn().getBody(Task.class);
                    }
                    exchange.getIn().setBody(task);
                })
                .log("Task deserializzato per update: ${body}")
                .process(exchange -> {
                    Task task = exchange.getIn().getBody(Task.class);
                    taskService.updateTask(task);
                    exchange.getMessage().setBody(task);
                });


        from("direct:deleteTask")
                .log("Eliminazione task con id: ${header.id}")
                .process(exchange -> {
                    Long id = Long.valueOf(exchange.getIn().getHeader("id", String.class));
                    taskService.deleteTask(id);
                    exchange.getMessage().setBody("Task eliminata con successo");
                });
    }
}
