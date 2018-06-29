package CorpFood.model.service.impl;


import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.UserResponseRepository;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserResponseImpl  implements UserResponseService{

    private UserResponseRepository userResponseRepository;

    @Autowired
    public UserResponseImpl(UserResponseRepository userResponseRepository){
        this.userResponseRepository = userResponseRepository;
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
        UserResponse userResponse = new UserResponse();

        userResponse.setYourOrder(createUserResponseDTO.getYourOrder());
        userResponse.setPrice(createUserResponseDTO.getPrice());
        return userResponseRepository.save(userResponse);
    }


}
