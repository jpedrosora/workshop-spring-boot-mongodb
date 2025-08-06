package com.zezin.workshopmongo.repository;

import com.zezin.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    //metodo alternativo
    //https://www.mongodb.com/pt-br/docs/manual/reference/operator/query/regex/
    //especificando uma consulta explicitamente com um json, ?0 para dizer que é o primeiro parametro recebido
    @Query("{'title' : {$regex: ?0, $options :  'i' } }")
    List<Post> searchTitle(String title);

    List<Post> findByTitleContainingIgnoreCase(String text);

    //buscando uma string que esteja ou no title, body, comments e com um periodo especifico de date
    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [ {'title' : {$regex: ?0, $options :  'i' } }, {'body' : {$regex: ?0, $options :  'i' } }, {'comments.text' : {$regex: ?0, $options :  'i' } } ] } ] }")
    List<Post> fullSearch(String text, Date minDate,  Date maxDate);
}
