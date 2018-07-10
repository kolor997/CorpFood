package CorpFood.controller;

import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AdminController {

    private UserRepository userRepository;
    private OfferService offerService;

    @Autowired
    public AdminController(UserRepository userRepository, OfferService offerService) {
        this.userRepository = userRepository;
        this.offerService = offerService;
    }

    @RequestMapping(value ="/adminPa", method = RequestMethod.GET)
    public ModelAndView addingNewOffer(ModelAndView model, Offer offer){
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


}
