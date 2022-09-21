package com.projeto.api.resources;

import com.projeto.api.domain.User;
import com.projeto.api.domain.dto.UserDTO;
import com.projeto.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/user")
public class UserResource {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }
    @GetMapping
     public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();// lista todos os users
        List<UserDTO> listDto = list.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());// vai mapear o obj x user e transformar em user dto
        return ResponseEntity.ok().body(listDto);//retorna a lista de users dto
    }
}
