package CorpFood.controller;

import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("/listResponses")
//    public String listResponses(Model model){
//        List<UserResponse> listedResponses = userResponseService.listAllUserResponses();
//        model.addAttribute("responses", listedResponses);
//
//        return "responses";
//    }

//    @GetMapping("/prices")
//    public BigDecimal getAllPrices(UserResponseDTO urdto) {
//
//        Set<BigDecimal> temp = new HashSet<>();
//
//        Set<UserResponse> all = userResponseService.findAll();
//
//        all.forEach(p-> temp.add(urdto.getPrice()));
//
//        return temp.stream()
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
}