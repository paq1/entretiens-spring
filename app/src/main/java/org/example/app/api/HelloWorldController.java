package org.example.app.api;

import org.example.app.dbo.Todo;
import org.example.app.repositories.TodoRepository;
import org.example.core.services.HelloWorldService;
import org.example.models.views.JsonApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
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

    @GetMapping("/")
    public ResponseEntity<JsonApi> hello() {

        this.todoRepository.insert(new Todo(UUID.randomUUID().toString(), "test", "test", true));

        return new ResponseEntity<>(new JsonApi(this.helloWorldService.sayHello()), HttpStatus.OK);
    }
}
