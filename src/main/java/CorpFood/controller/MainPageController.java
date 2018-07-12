package CorpFood.controller;

import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.OfferDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.entity.User;
import CorpFood.model.entity.UserResponse;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.OfferService;
import CorpFood.model.service.UserResponseService;
import CorpFood.model.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainPageController {

    private UserResponseService userResponseService;
    private OfferService offerService;
    private UserRepository userRepository; //userService
    private ContentServiceImpl contentService;


    @Autowired
    public MainPageController(UserResponseService userResponseService, OfferService offerService, UserRepository userRepository, ContentServiceImpl contentService) {
        this.userResponseService = userResponseService;
        this.offerService = offerService;
        this.userRepository = userRepository;
        this.contentService = contentService;
    }



    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserResponse userResponse) {
        UserDetails userPrincipals = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findOneByLogin(userPrincipals.getUsername());
        Set<UserResponse> response = userResponseService.findAll();

        List<Offer> offers = offerService.findActiveOffers();
        Map<OfferDTO, Set<UserResponse>> activeResponses =  contentService.getAllFoodOrder();

        modelAndView.addObject("activeResponses",activeResponses);
        modelAndView.addObject("activeOffers", offers);
        modelAndView.addObject("responses", response);

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

        List<Offer> offers = offerService.findActiveOffers();
        Map<OfferDTO, Set<UserResponse>> activeResponses =  contentService.getAllFoodOrder();

        modelAndView.addObject("activeResponses",activeResponses);
        modelAndView.addObject("activeOffers", offers);

        Set<UserResponse> response = userResponseService.findAll();
        modelAndView.addObject("responses", response);
        userResponseService.createUserResponse(userRes);
        modelAndView.addObject("userR", new UserResponse());
        modelAndView.setViewName("FirstPage");
        return modelAndView;
    }

}
