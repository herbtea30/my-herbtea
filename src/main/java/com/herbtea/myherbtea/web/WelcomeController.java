package com.herbtea.myherbtea.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(String name, int age, String sex, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("sex", sex);
        return "welcome";
    }
}
