package com.zezin.workshopmongo.resources;

import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    //objeto sofisticado do spring que encapsula a estrutura necessario para retorna resposta http com possiveis cabecalhos, erros e etc
    public ResponseEntity<List<User>> findAll() {
        //busca os users no banco de dados e guarda na lista e devolvemos na resposta da requisicao
        List<User> list = service.findAll();
        //ok instancia o responseEntity ja com o codigo HTTP
        //body indentifica o corpo da resposta
        return ResponseEntity.ok().body(list);
    }
}
