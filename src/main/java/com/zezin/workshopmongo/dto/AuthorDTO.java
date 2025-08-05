package com.zezin.workshopmongo.dto;

import com.zezin.workshopmongo.domain.User;

import java.io.Serial;
import java.io.Serializable;

//criacao dessa classe para desnormalização dos dados

public class AuthorDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO() {}

    public AuthorDTO(User obj) {
        id = obj.getId();
        name = obj.getName();
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {}

}
