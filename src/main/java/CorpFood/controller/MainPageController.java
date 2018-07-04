package CorpFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/welcome")
    public String pleas(ModelMap modelMap) {
        modelMap.put("one", "Finally working!!!!!!");
        return "FirstPage";
    }

}
