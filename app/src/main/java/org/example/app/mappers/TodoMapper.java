package org.example.app.mappers;

import org.example.app.dbo.Todo;
import org.example.models.views.TodoView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(target = "status", source = "todo.done")
    TodoView todoToTodoView(Todo todo);

    @Mapping(target = "done", source = "todo.status")
    Todo todoViewToTodo(TodoView todo);
}
