package com.alexgiounan.todo.mapper;

import com.alexgiounan.todo.dto.TodoDto;
import com.alexgiounan.todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    Todo todoDtoToTodo(TodoDto todoDto);

    TodoDto todoToTodoDto(Todo todo);

    List<Todo> todoDtoToTodo(List<TodoDto> todoDtoList);

    List<TodoDto> todoToTodoDto(List<Todo> todoList);
}
