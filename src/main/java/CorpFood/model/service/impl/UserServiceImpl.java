package CorpFood.model.service.impl;


import CorpFood.model.dto.CreateUserDto;
import CorpFood.model.entity.User;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findOneById(Long id){return userRepository.getOne(id);}

    @Override
    public Set<User> findAll() {return new HashSet<>(userRepository.findAll());}

    @Override
    public void deleteUser(Long id) {userRepository.delete(id);}

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setLogin(createUserDto.getLogin());
        user.setPassword(createUserDto.getPassword());
        return userRepository.save(user);
    }
}
