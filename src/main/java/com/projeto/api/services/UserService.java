package com.projeto.api.services;

import com.projeto.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll ();
}
