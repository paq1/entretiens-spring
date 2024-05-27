package org.example.app.dbo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("todolist")
public class Todo {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean done;

    public Todo(String id, String title, String description, boolean done) {

        super();

        this.id = id;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public String getTitle() {
        return title;
    }
}
