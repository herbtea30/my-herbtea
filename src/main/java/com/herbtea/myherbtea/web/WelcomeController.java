package com.herbtea.myherbtea.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {
    @GetMapping("/helloworld")
    public String welcome(Model model) {
        List<MyModel> models = Arrays.asList(new MyModel("dony"), new MyModel("herbtea"), new MyModel("cheesena"));
        model.addAttribute("name", "herbta");
        model.addAttribute("age", 10);
        model.addAttribute("sex", "male");
        model.addAttribute("taxed_value", 10);
        model.addAttribute("in_ca", true);
        model.addAttribute("models", models);
        return "welcome";
    }
}
