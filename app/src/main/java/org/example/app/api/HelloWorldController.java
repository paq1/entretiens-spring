package org.example.app.api;

import org.example.app.dbo.Todo;
import org.example.app.repositories.TodoRepository;
import org.example.core.services.HelloWorldService;
import org.example.models.views.JsonApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;
    private final TodoRepository todoRepository;

    public HelloWorldController(
            HelloWorldService helloWorldService,
            TodoRepository todoRepository
    ) {
        this.helloWorldService = helloWorldService;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonApi<String>> hello(@PathVariable String id) {

        Optional<ResponseEntity<JsonApi<String>>> optResp = this
                .todoRepository
                .findFirstByTitle(id)
                .stream()
                .map(x -> new ResponseEntity<>(new JsonApi<>(x.getTitle()), HttpStatus.OK))
                .findFirst();

        if (optResp.isPresent()) {
            return optResp.get();
        } else {
            // fixme faire un mapping pour l'erreur
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", new Exception("pas trouvé"));
        }
    }

    @PostMapping("/")
    public ResponseEntity<JsonApi<Todo>> hello(@RequestBody Todo todo) {
        Todo result = this.todoRepository.insert(todo);
        return ResponseEntity.status(201).body(new JsonApi<>(result));
    }
}
