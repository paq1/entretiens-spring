package org.example.app.api;

import org.example.app.dbo.Todo;
import org.example.app.mappers.TodoMapper;
import org.example.app.repositories.TodoRepository;
import org.example.models.commands.CreateTodo;
import org.example.models.views.Many;
import org.example.models.views.Single;
import org.example.models.views.TodoView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoController(
            TodoRepository todoRepository,
            TodoMapper todoMapper
    ) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Single<TodoView>> fetchOne(@PathVariable String id) {
        return this
                .todoRepository
                .findByTitle(id)
                .map(x -> ResponseEntity.of(Optional.of(new Single<>(todoMapper.todoToTodoView(x)))))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<Many<TodoView>> fetchMany() {
        List<TodoView> todos = this
                .todoRepository
                .findAll()
                .stream()
                .map(todoMapper::todoToTodoView)
                .toList();

        return ResponseEntity.ok(new Many<>(todos));
    }

    @PostMapping("/")
    public ResponseEntity<Single<TodoView>> insertOne(@RequestBody CreateTodo todo) {

        Todo todoEntity = new Todo(todo.title(), todo.description(), todo.status());
        Todo result = this.todoRepository.save(todoEntity);
        return ResponseEntity.status(201).body(new Single<>(todoMapper.todoToTodoView(result)));
    }
}
