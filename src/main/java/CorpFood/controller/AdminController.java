package CorpFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminPa")
    public String admin() {
        return "AdminPage";
    }

}
