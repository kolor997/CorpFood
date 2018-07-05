package CorpFood.controller;

import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.service.UserResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MainPageController {

    private UserResponseService userResponseService;


    @Autowired
    public MainPageController(UserResponseService userResponseService) {
        this.userResponseService = userResponseService;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserResponse userResponse) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        modelAndView.addObject("userHeader", username);
        modelAndView.addObject("userR", userResponse);
        modelAndView.setViewName("FirstPage");
        return modelAndView;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid CreateUserResponseDTO userRes) {
        ModelAndView modelAndView = new ModelAndView();
        userResponseService.createUserResponse(userRes);

        modelAndView.addObject("userR", new UserResponse());
        modelAndView.setViewName("FirstPage");
        return modelAndView;
    }

}
