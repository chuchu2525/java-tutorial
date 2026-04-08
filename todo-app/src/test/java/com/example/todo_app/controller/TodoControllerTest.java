package com.example.todo_app.controller;

import com.example.todo_app.model.Todo;
import com.example.todo_app.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.deleteAll();
    }

    @Test
    void shouldCreateTodo() throws Exception {
        String todoJson = "{\"title\": \"Test Todo\", \"completed\": false}";

        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Todo")))
                .andExpect(jsonPath("$.completed", is(false)));
    }

    @Test
    void shouldGetAllTodos() throws Exception {
        todoRepository.save(new Todo(null, "Todo 1", false));
        todoRepository.save(new Todo(null, "Todo 2", true));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Todo 1")))
                .andExpect(jsonPath("$[1].title", is("Todo 2")));
    }

    @Test
    void shouldGetTodoById() throws Exception {
        Todo todo = todoRepository.save(new Todo(null, "Test Todo", false));

        mockMvc.perform(get("/api/todos/" + todo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Todo")));
    }

    @Test
    void shouldUpdateTodo() throws Exception {
        Todo todo = todoRepository.save(new Todo(null, "Old Title", false));
        String updatedTodoJson = "{\"title\": \"New Title\", \"completed\": true}";

        mockMvc.perform(put("/api/todos/" + todo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTodoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Title")))
                .andExpect(jsonPath("$.completed", is(true)));
    }

    @Test
    void shouldDeleteTodo() throws Exception {
        Todo todo = todoRepository.save(new Todo(null, "To be deleted", false));

        mockMvc.perform(delete("/api/todos/" + todo.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/todos/" + todo.getId()))
                .andExpect(status().isNotFound());
    }
}
