package com.projeto.api.resources;

import com.projeto.api.domain.User;
import com.projeto.api.domain.dto.UserDTO;
import com.projeto.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/user")
public class UserResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }
    @GetMapping
     public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();// lista todos os users
        List<UserDTO> listDto = list.stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList());// vai mapear o obj x user e transformar em user dto
        return ResponseEntity.ok().body(listDto);//retorna a lista de users dto
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj ) {
        User newObj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
      obj.setId(id);
      User newObj = service.update(obj);
      return ResponseEntity.ok().body(mapper.map(newObj,UserDTO.class));
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }
}
