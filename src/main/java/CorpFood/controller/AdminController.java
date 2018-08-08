package CorpFood.controller;

import CorpFood.mail.EmailSender;
import CorpFood.model.dto.CreateOfferDTO;
import CorpFood.model.dto.UserResponseDTO;
import CorpFood.model.entity.Offer;
import CorpFood.model.repository.UserRepository;
import CorpFood.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AdminController {

    private UserRepository userRepository;
    private OfferService offerService;
    private ContentService contentService;
    private UserResponseService userResponseService;
    private UserService userService;
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public AdminController(UserRepository userRepository, OfferService offerService, ContentService contentService, UserResponseService userResponseService, UserService userService, EmailSender emailSender, TemplateEngine templateEngine) {
        this.userRepository = userRepository;
        this.offerService = offerService;
        this.contentService = contentService;
        this.userResponseService = userResponseService;
        this.userService = userService;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @RequestMapping(value ="/adminPa", method = RequestMethod.GET)
    public ModelAndView showPageContent(ModelAndView model, Offer offer){

        Map<String, Set<UserResponseDTO>> activeResponses = contentService.getAllFoodOrder();
        List<Offer> offers = offerService.findActiveOffers();

        model.addObject("keys", activeResponses.keySet());
        model.addObject("activeResponses", activeResponses);
        model.addObject("users", userRepository.findAll());
        model.addObject("activeOffers", offers);
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

    @RequestMapping(value = "/cancelDebt/{id}", method = RequestMethod.POST)
    public String cancelDebt(@PathVariable Long id) {
        userService.cancelDebt(id);
        return "redirect:/adminPa";
    }

    @RequestMapping(value = "/setExpirationTime", method = RequestMethod.POST)
    public String setExpirationTime(@ModelAttribute(value = "offer") Offer offer){
        offerService.setExpirationTime(offer.getExpirationTime());
        return "redirect:/adminPa";
    }

    @RequestMapping(value = "/sendSummaryEmail", method = RequestMethod.POST)
    public String send(@ModelAttribute(value = "offer") Offer offer) throws MessagingException {

        String emailAddress = offer.getEmail();

        Context context = new Context();
        context.setVariable("header", "");
        context.setVariable("title", "New CorpFood orders are ready to go!");
        context.setVariable("descriptions", contentService.getAllFoodOrder());
        context.setVariable("price", contentService.getAllPrices());

        String body = templateEngine.process("mailTemplate", context);
        emailSender.sendEmail(emailAddress, "CorpFood orders are ready to go!", body);
        return "redirect:/adminPa";
    }

}
