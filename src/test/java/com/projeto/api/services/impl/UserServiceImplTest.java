package com.projeto.api.services.impl;

import com.projeto.api.domain.User;
import com.projeto.api.domain.dto.UserDTO;
import com.projeto.api.repositories.UserRepository;
import com.projeto.api.services.exceptions.DataIntegratyViolationException;
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

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Cynthia";
    public static final String EMAIL = "cynthia@email.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;
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

        assertEquals(ID, response.getId());assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());//comparação com o id
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
    }
    @Test //teste quando o objeto não for encontrado
    void whenFindByIdThenReturnNotFound(){
        when(repository.findById((anyInt()))).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
        try{
            service.findById(ID);
        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO,ex.getMessage());
        }
    }

    @Test // teste para quando buscar retornar uma lista de users
    void whenFindAllThenReturnListUsers() {
       when(repository.findAll()).thenReturn(List.of(user));
       List<User> response = service.findAll();
       assertNotNull(response);
       assertEquals(1,response.size());//para vir apenas um usuario
       assertEquals(User.class, response.get(INDEX).getClass());

       assertEquals(ID, response.get(INDEX).getId());
       assertEquals(NAME, response.get(INDEX).getName());
       assertEquals(EMAIL, response.get(INDEX).getEmail());
       assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test // teste quando tiver sucesso na criação
    void whenCreateReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());


    }

    @Test // teste quando tiver violação na integridade dos dados retorne exceção
    void whenCreateReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
           assertEquals(DataIntegratyViolationException.class, ex.getClass());
           assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }

    }

    @Test // teste quando tiver sucesso na criação
    void whenUpdateReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());


    }
    @Test // teste quando tiver violação na integridade dos dados retorne exceção
    void whenUpdateReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }

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