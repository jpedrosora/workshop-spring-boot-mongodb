package com.zezin.workshopmongo.repository;

import com.zezin.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//mongorepository precisa do tipo da classe de dominio que sera
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
