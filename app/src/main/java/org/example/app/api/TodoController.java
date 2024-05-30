package org.example.app.api;

import org.example.app.dbo.Todo;
import org.example.app.repositories.TodoRepository;
import org.example.models.views.Many;
import org.example.models.views.Single;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(
            TodoRepository todoRepository
    ) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Single<Todo>> fetchOne(@PathVariable String id) {
        return this
                .todoRepository
                .findByTitle(id)
                .map(x -> ResponseEntity.of(Optional.of(new Single<>(x))))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<Many<Todo>> fetchMany() {
        List<Todo> todos = this
                .todoRepository
                .findAll();
        return ResponseEntity.ok(new Many<>(todos));
    }

    @PostMapping("/")
    public ResponseEntity<Single<Todo>> insertOne(@RequestBody Todo todo) {
        Todo result = this.todoRepository.save(todo);
        return ResponseEntity.status(201).body(new Single<>(result));
    }
}
