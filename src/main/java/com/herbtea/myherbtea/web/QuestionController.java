package com.herbtea.myherbtea.web;

import com.herbtea.myherbtea.domain.Question;
import com.herbtea.myherbtea.domain.QuestionRepository;
import com.herbtea.myherbtea.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (!HttpSessionUtils.isLoginUser(session)) {
            return "/uesrs/loginForm";
        }
        return "/qna/form";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session) {
        if(!HttpSessionUtils.isLoginUser(session)) {
            return "/users/loginForm";
        }
        User sessionUser = HttpSessionUtils.getUserFromSession(session);
        Question question = new Question(sessionUser.getName(), title, contents);
        questionRepository.save(question);

        return "redirect:/";
    }
}
