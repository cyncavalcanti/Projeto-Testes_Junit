package com.projeto.api.services.impl;

import com.projeto.api.domain.User;
import com.projeto.api.domain.dto.UserDTO;
import com.projeto.api.repositories.UserRepository;
import com.projeto.api.services.exceptions.ObjectNotFoundException;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Cynthia";
    public static final String EMAIL = "cynthia@email.com";
    public static final String PASSWORD = "123";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Startuser();
    }

    @Test //teste para quando buscar o Id retornar uma instancia de usuário
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        Assertions.assertEquals(ID, response.getId());assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());//comparação com o id
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }
    @Test //teste quando o objeto não for encontrado
    void whenFindByIdThenReturnNotFound(){
        when(repository.findById((anyInt()))).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado",ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {

    }
    private void Startuser(){ //vai startar os valores dos atributos dos usuários
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

    }
}