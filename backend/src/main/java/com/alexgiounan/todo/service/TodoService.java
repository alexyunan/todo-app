package com.alexgiounan.todo.service;

import com.alexgiounan.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo addTodo(Todo todo);

    Todo getTodo(Long id);

    List<Todo> getAllTodos();

    void updateTodo(Todo todo);

    void deleteTodo(Long id);

    Todo completedTodo(Long id);

    Todo inCompleteTodo(Long id);
}
