package org.example.app.repositories;

import org.example.app.dbo.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    @Query("{title:'?0'}")
    Todo findItemByName(String title);

    @Query(value="{title:'?0'}", fields="{'title' : 1, 'description' : 1}")
    List<Todo> findAll(String title);

    public long count();

}
