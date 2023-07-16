package com.alexgiounan.todo.controller;

import com.alexgiounan.todo.dto.TodoDto;
import com.alexgiounan.todo.mapper.TodoMapper;
import com.alexgiounan.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto newTodo = todoMapper.todoToTodoDto(todoService.addTodo(todoMapper.todoDtoToTodo(todoDto)));
        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> findTodoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(todoMapper.todoToTodoDto(todoService.getTodo(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> findAllTodos(){
        return ResponseEntity.ok(todoMapper.todoToTodoDto(todoService.getAllTodos()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<String>updateTodo(@RequestBody TodoDto todoDto){
       todoService.updateTodo(todoMapper.todoDtoToTodo(todoDto));
       return ResponseEntity.ok("Todo updated successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/complete/{id}")
    public ResponseEntity<TodoDto> completedTodo(@PathVariable("id")Long id){
        return ResponseEntity.ok(todoMapper.todoToTodoDto(todoService.completedTodo(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/in-complete/{id}")
    public ResponseEntity<TodoDto> inCompletedTodo(@PathVariable("id")Long id){
        return ResponseEntity.ok(todoMapper.todoToTodoDto(todoService.inCompleteTodo(id)));
    }
}
