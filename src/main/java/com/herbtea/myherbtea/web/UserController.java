package com.herbtea.myherbtea.web;

import com.herbtea.myherbtea.domain.User;
import com.herbtea.myherbtea.domain.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    //private List<User> users = new ArrayList<>();
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session) {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            System.out.println("Login Failure!");
            return "redirect:/users/loginForm";
        }
        if(!password.equals(user.getPassword())) {
            System.out.println("Login Failure!");
            return "redirect:/users/loginForm";
        }
        System.out.println("Login Success");
        session.setAttribute("user", user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");

        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable Long id, Model model, HttpSession session) throws IllegalAccessException {
        Object tempUser = session.getAttribute("user");
        if(tempUser == null) {
            return "redirect:/users/loginForm";
        }
        User sessionedUser = (User) tempUser;
        if(!id.equals(sessionedUser.getId())) {
            throw new IllegalAccessException("자신의 정보만 수정할 수 있습니다.");
        }

        Optional<User> user = userRepository.findById(id);
        model.addAttribute("users", user.get());
        return "/user/updateForm";
    }

    @PostMapping("")
    public String create(User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/user/list";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, User updateUser, HttpSession session) throws IllegalAccessException {
        Object tempUser = session.getAttribute("user");
        if(tempUser == null) {
            return "redirect:/users/loginForm";
        }
        User sessionedUser = (User) tempUser;
        if(!id.equals(sessionedUser.getId())) {
            throw new IllegalAccessException("자신의 정보만 수정할 수 있습니다.");
        }
        Optional<User> user = userRepository.findById(id);

        user.get().update(updateUser);
        userRepository.save(user.get());
        return "redirect:/users";
    }
}
