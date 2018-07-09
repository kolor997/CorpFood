package CorpFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotFoundController {
    @RequestMapping("/401.html")
    public String render404() {

        return "Error401";
    }
}