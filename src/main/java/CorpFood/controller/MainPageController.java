package CorpFood.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String foo(ModelMap model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        model.put("userName", username);
        return "FirstPage";
    }

}
