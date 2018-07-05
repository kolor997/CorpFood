package CorpFood.controller;

import CorpFood.model.dto.CreateUserDto;
import CorpFood.model.entity.User;
import CorpFood.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SignUpController {

    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("SignUp");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid CreateUserDto user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.createUser(user);

        modelAndView.addObject("user", new User());
        modelAndView.setViewName("LoginPage");
        return modelAndView;
    }
}

