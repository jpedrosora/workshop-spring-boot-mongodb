package com.zezin.workshopmongo.services;

import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.dto.UserDTO;
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

    public User insert(User obj) {
        return repo.insert(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User update(User obj) {
        //esse obj ele vai ser os dados enviados na requisition, ainda não tem vinculo com o DB
        //vamos buscar primeiro o obj no banco de dados, alterar e depois salvar
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    //dependendo da situação pra instanciar um user podemos querer acessar o banco de dados e quem ja tem a dependencia do banco de dados é o UserService
    //para ficar uma situção que seja possivel o acesso futuro aos dados vamos colocar o metodo aqui
    public User fromDTO(UserDTO objDTO) {
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }

}
