package com.projeto.api.services;

import com.projeto.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
