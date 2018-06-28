package CorpFood.model.service;

import CorpFood.model.dto.CreateUserDto;
import CorpFood.model.entity.User;

import java.util.Set;

public interface userService {

    User findOneById(Long id);
    Set<User> findAll();
    void deleteUser(Long id);
    User createUser(CreateUserDto createUserDto);
}
