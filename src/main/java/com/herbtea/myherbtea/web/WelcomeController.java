package com.herbtea.myherbtea.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(Model model) {
        model.addAttribute("name", "herbta");
        model.addAttribute("age", 10);
        model.addAttribute("sex", "male");
        model.addAttribute("taxed_value", 10);
        model.addAttribute("in_ca", true);
        return "welcome";
    }
}
