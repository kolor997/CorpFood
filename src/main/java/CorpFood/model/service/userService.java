package CorpFood.model.service;

import CorpFood.model.entity.User;

import java.util.Set;

public interface userService {

    User findOneById(Long id);
    Set<User> findAll();
    User deleteUser(Long id);
    User createUser();
}
