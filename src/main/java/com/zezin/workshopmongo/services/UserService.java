package com.zezin.workshopmongo.services;

import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.repository.UserRepository;
import com.zezin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
