package com.zezin.workshopmongo.services;

import com.zezin.workshopmongo.domain.Post;
import com.zezin.workshopmongo.domain.User;
import com.zezin.workshopmongo.dto.UserDTO;
import com.zezin.workshopmongo.repository.PostRepository;
import com.zezin.workshopmongo.repository.UserRepository;
import com.zezin.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text){
        return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        //acrescentar um dia na data maxima, pois a data maxima é gerada no dia informado so que a meia noite, assim consideramos ate o final daquele dia
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
