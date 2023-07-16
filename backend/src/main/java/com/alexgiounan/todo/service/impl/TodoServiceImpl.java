package com.alexgiounan.todo.service.impl;

import com.alexgiounan.todo.entity.Todo;
import com.alexgiounan.todo.exception.ResourceNotFoundException;
import com.alexgiounan.todo.repository.TodoRepository;
import com.alexgiounan.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo getTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("todo with id -> %s not found".formatted(id)));
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public void updateTodo(Todo todo) {
        Todo existingTodo = todoRepository.findById(todo.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Todo with id -> %s not found".formatted(todo.getId()))
        );

        existingTodo.setDescription(todo.getDescription());
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setCompleted(todo.isCompleted());

        todoRepository.save(existingTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo completedTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo with id -> %s not found".formatted(id))
        );

        existingTodo.setCompleted(Boolean.TRUE);
        return todoRepository.save(existingTodo);
    }

    @Override
    public Todo inCompleteTodo(Long id) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo with id -> %s not found".formatted(id))
        );

        existingTodo.setCompleted(Boolean.FALSE);
        return todoRepository.save(existingTodo);
    }
}
