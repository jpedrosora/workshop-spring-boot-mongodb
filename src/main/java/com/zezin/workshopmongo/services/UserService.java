package com.zezin.workshopmongo.services;

import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//anotação para identificar que sera um servico injetavel em outras classes
@Service
public class UserService {

    //anotacao para instanciar automaticamente um objeto, o proprio spring procura a definicao do objeto e instancia
    //injecao de dependencia automatica
    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
        return repo.findAll();
    }

}
