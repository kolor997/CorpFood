package CorpFood.controller;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/corpFood/userResp")
public class UserResponseController {

    private UserResponseService userResponseService;

    @Autowired
    public UserResponseController(UserResponseService userResponseService) {
        this.userResponseService = userResponseService;
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(Long id) {
        UserResponse userResponse = userResponseService.findOneById(id);
        return new UserResponseDTO(userResponse);
    }

    @GetMapping("/")
    public Set<UserResponseDTO> findAll() {
        Set<UserResponseDTO> result = new HashSet<>();

        Set<UserResponse> all = userResponseService.findAll();
        all.forEach(b -> result.add(new UserResponseDTO(b)));

        return result;
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody CreateUserResponseDTO createUserResponseDTO) {
        return new UserResponseDTO(userResponseService.createUserResponse(createUserResponseDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteUserResponse(@PathVariable Long id) {
        userResponseService.deleteUserResponse(id);
    }


}