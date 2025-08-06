package com.zezin.workshopmongo.repository;

import com.zezin.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //metodo alternativo
    //https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/
    //especificando uma consulta explicitamente com um json, ?0 para dizer que é o primeiro parametro recebido
    @Query("{'title' : {$regex: ?0, $options :  'i' } }")
    List<Post> searchTitle(String title);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
