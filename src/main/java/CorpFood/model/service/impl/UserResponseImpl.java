package CorpFood.model.service.impl;


import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.entity.User;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.OfferRepository;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.repository.UserResponseRepository;
import CorpFood.model.service.OfferService;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserResponseImpl implements UserResponseService {

    private UserResponseRepository userResponseRepository;
    private UserRepository userRepository;
    private OfferService offerService;

    @Autowired
    public UserResponseImpl(UserResponseRepository userResponseRepository, UserRepository userRepository, OfferService offerService) {
        this.userResponseRepository = userResponseRepository;
        this.userRepository = userRepository;
        this.offerService = offerService;
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
        String username =  SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findOneByLogin(username);
        Offer offer = offerService.findOneById(createUserResponseDTO.getOfferID());
        UserResponse userResponse = new UserResponse();
        userResponse.setOffer(offer);
        userResponse.setYourOrder(createUserResponseDTO.getYourOrder());
        userResponse.setUser(user);
        userResponse.setPrice(createUserResponseDTO.getPrice());
        return userResponseRepository.save(userResponse);
    }

    @Override
    public void deleteUserResponse(Long id) {
        userResponseRepository.delete(id);
    }

    @Override
    public List<UserResponse> listAllUserResponses() {
        List<UserResponse> responses= userResponseRepository.findAll();
        return responses;
    }

}
