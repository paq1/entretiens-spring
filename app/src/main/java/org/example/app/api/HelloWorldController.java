package org.example.app.api;

import org.example.core.services.HelloWorldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/")
    public ResponseEntity<JsonApi> hello() {
        return new ResponseEntity<>(new JsonApi(this.helloWorldService.sayHello()), HttpStatus.OK);
    }
}
