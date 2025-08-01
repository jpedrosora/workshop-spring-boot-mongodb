package com.zezin.workshopmongo.resources;

import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.dto.UserDTO;
import com.zezin.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    //objeto sofisticado do spring que encapsula a estrutura necessario para retorna resposta http com possiveis cabecalhos, erros e etc
    public ResponseEntity<List<UserDTO>> findAll() {
        //busca os users no banco de dados e guarda na lista e devolvemos na resposta da requisicao
        List<User> list = service.findAll();
        //convertendo de User para User DTO
        List<UserDTO> listDto =  list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        //ok instancia o responseEntity ja com o codigo HTTP
        //body indentifica o corpo da resposta
        return ResponseEntity.ok().body(listDto);
    }
    @RequestMapping(value ="/{id}", method  = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        //para informar que o id recebido no metodo tem que ser igual ao da URL usamos @PathVariable
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inset(@RequestBody UserDTO objDTO) {
        User obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        //retornar uma resposta vazia porem um cabeçalho com a URL do novo recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @RequestMapping(value ="/{id}", method  = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
        User obj = service.fromDTO(objDTO);
        //garantit que o objeto vai ter o ID da requisicao
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
}
