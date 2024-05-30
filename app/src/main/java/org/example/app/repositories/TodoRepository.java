package org.example.app.repositories;

import org.example.app.dbo.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends CrudRepository<Todo, String> {


    Optional<Todo> findByTitle(String title);
    List<Todo> findAll();

//    default Optional<Todo> findFirstByTitle(String title) {
//        return
//    }
//    @Query("{title:'?0'}")
//    List<Todo> findItemByName(String title);
//    @Query(value="{title:'?0'}", fields="{'title' : 1, 'description' : 1}")
//    List<Todo> findAll(String title);
//    public long count();

}
