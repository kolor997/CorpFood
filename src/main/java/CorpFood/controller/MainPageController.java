package CorpFood.controller;

import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.entity.User;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainPageController {

    private UserResponseService userResponseService;
    private UserRepository userRepository;


    @Autowired
    public MainPageController(UserResponseService userResponseService, UserRepository userRepository) {
        this.userResponseService = userResponseService;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserResponse userResponse) {
        UserDetails userPrincipals = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findOneByLogin(userPrincipals.getUsername());
        modelAndView.addObject("userHeader", user.getFirstName() + " " + user.getLastName());
        modelAndView.addObject("userR", userResponse);
        modelAndView.setViewName("FirstPage");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid CreateUserResponseDTO userRes) {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails userPrincipals = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findOneByLogin(userPrincipals.getUsername());
        modelAndView.addObject("userHeader", user.getFirstName() + " " + user.getLastName());

        userResponseService.createUserResponse(userRes);
        modelAndView.addObject("userR", new UserResponse());
        modelAndView.setViewName("FirstPage");
        return modelAndView;
    }

}
