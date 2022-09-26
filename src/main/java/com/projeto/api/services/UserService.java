package com.projeto.api.services;

import com.projeto.api.domain.User;
import com.projeto.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll ();
    User create(UserDTO obj);
    User update (UserDTO obj);
}
