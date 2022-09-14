package com.projeto.api.config;

import com.projeto.api.domain.User;
import com.projeto.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

//configuração do perfil de testes
@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public void startDB(){
        User usuario1 = new User(null,"Maria","maria@email.com","123");
        User usuario2 = new User(null,"José","jose@email.com","123");
        repository.saveAll(List.of(usuario1,usuario2));


//        User usuario1 = new User(null,"Cynthia","cynthia@email.com","123");
//        User usuario2 = new User(null,"José","jose@email.com","123");
//        repository.saveAll(List.of(usuario1,usuario2));
    }
}
