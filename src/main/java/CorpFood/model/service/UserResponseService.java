package CorpFood.model.service;

import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.entity.UserResponse;

import java.util.List;
import java.util.Set;

public interface UserResponseService {

    UserResponse findOneById(Long id);

    Set<UserResponse> findAll();

    UserResponse createUserResponse(CreateUserResponseDTO createUserResponseDTO);

    void deleteUserResponse(Long id);

    void deleteUserResponsesByUserId(Long id);

    List<UserResponse> listAllUserResponses();


}
