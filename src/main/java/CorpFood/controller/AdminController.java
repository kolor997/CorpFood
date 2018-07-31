package CorpFood.controller;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.CreateUserResponseDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.ContentService;
import CorpFood.model.service.OfferService;
import CorpFood.model.service.UserResponseService;
import CorpFood.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Controller
public class AdminController {

    private UserRepository userRepository;
    private OfferService offerService;
    private ContentService contentService;
    private UserResponseService userResponseService;
    private UserService userService;

    @Autowired
    public AdminController(UserRepository userRepository, OfferService offerService, ContentService contentService, UserResponseService userResponseService, UserService userService) {
        this.userRepository = userRepository;
        this.offerService = offerService;
        this.contentService = contentService;
        this.userResponseService = userResponseService;
        this.userService = userService;
    }

    @RequestMapping(value ="/adminPa", method = RequestMethod.GET)
    public ModelAndView showActiveResponses(ModelAndView model, Offer offer){

        Map<String, Set<UserResponseDTO>> activeResponses = contentService.getAllFoodOrder();

        model.addObject("keys", activeResponses.keySet());
        model.addObject("activeResponses", activeResponses);
        model.addObject("users", userRepository.findAll());
        model.addObject("newOffer", offer);
        model.setViewName("AdminPage");
        return model;
    }

    @RequestMapping(value ="/adminPa", method = RequestMethod.POST)
    public ModelAndView newOffer(@Valid CreateOfferDTO offer){
        ModelAndView modelAndView = new ModelAndView();
        offerService.createOffer(offer);
        modelAndView.addObject("users", userRepository.findAll());
        modelAndView.addObject("offer", new Offer());
        modelAndView.setViewName("AdminPage");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUserResponse/{id}", method = RequestMethod.POST)
    public String deleteUserResponse(@PathVariable Long id) {
        userResponseService.deleteUserResponse(id);
        return "redirect:/adminPa";
    }

    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable Long id) {
        userResponseService.deleteUserResponsesByUserId(id);
        userService.deleteUser(id);
        return "redirect:/adminPa";
    }

}
