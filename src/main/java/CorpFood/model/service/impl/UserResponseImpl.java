package CorpFood.model.service.impl;


import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.User;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.repository.UserResponseRepository;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserResponseImpl implements UserResponseService {

    private UserResponseRepository userResponseRepository;
    private UserRepository userRepository;

    @Autowired
    public UserResponseImpl(UserResponseRepository userResponseRepository, UserRepository userRepository) {
        this.userResponseRepository = userResponseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse findOneById(Long id) {
        return userResponseRepository.findOne(id);
    }

    @Override
    public Set<UserResponse> findAll() {
        return new HashSet<>(userResponseRepository.findAll());
    }


    @Override
    public UserResponse createUserResponse(CreateUserResponseDTO createUserResponseDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOneByLogin(username);
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(user);
        userResponse.setYourOrder(createUserResponseDTO.getYourOrder());
        userResponse.setPrice(createUserResponseDTO.getPrice());
        return userResponseRepository.save(userResponse);
    }

    @Override
    public void deleteUserResponse(Long id) {
        userResponseRepository.delete(id);
    }

}
