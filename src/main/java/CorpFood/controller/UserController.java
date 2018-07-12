package CorpFood.controller;

import CorpFood.model.dto.CreateUserDto;
import CorpFood.model.dto.UserDto;
import CorpFood.model.entity.User;
import CorpFood.model.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/corpFood/users")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto findById(Long id){

        User user = userService.findOneById(id);
        return new UserDto(user);
    }

    @GetMapping
    public Set<UserDto> findAll() {
        Set<UserDto> result = new HashSet<>();

        Set<User> all = userService.findAll();
        all.forEach(b -> result.add(new UserDto(b)));

        return result;
    }

    @PostMapping
    public UserDto create(@RequestBody CreateUserDto createUser) {
        return new UserDto(userService.createUser(createUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
