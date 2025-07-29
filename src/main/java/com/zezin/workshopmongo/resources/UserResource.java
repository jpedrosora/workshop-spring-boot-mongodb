package com.zezin.workshopmongo.resources;

import com.zezin.workshopmongo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET)
    //objeto sofisticado do spring que encapsula a estrutura necessario para retorna resposta http com possiveis cabecalhos, erros e etc
    public ResponseEntity<List<User>> findAll(){
        User maria = new User("1", "Maria Brown","maria@gmail.com");
        User alex = new User("2", "Alex Green", "alex@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria,alex));
        //ok instancia o responseEntity ja com o codigo HTTP
        //body indentifica o corpo da resposta
        return ResponseEntity.ok().body(list);
    }
}
