package com.herbtea.myherbtea.web;

import com.herbtea.myherbtea.domain.Question;
import com.herbtea.myherbtea.domain.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private QuestionRepository questionRepository;

    public HomeController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("")
    public String home (Model model) {
        model.addAttribute("questions", questionRepository.findAll());
        return "index";
    }

    @GetMapping("test")
    public String test() {
        return "";
    }

}
