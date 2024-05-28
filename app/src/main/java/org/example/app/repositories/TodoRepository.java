package org.example.app.repositories;

import org.example.app.dbo.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends MongoRepository<Todo, String> {


    default Optional<Todo> findFirstByTitle(String title) {
        return findItemByName(title).stream().findFirst();
    }

    @Query("{title:'?0'}")
    List<Todo> findItemByName(String title);

    @Query(value="{title:'?0'}", fields="{'title' : 1, 'description' : 1}")
    List<Todo> findAll(String title);

    public long count();

}
