package com.example.movieticket.controller;

import com.example.movieticket.model.User;
import com.example.movieticket.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService){ this.userService = userService; }

    @GetMapping("/login")
    public String login(){ return "login"; }

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user, Model model){
        if(user.getUsername()==null || user.getPassword()==null){
            model.addAttribute("error","Provide email & password");
            return "signup";
        }
        userService.register(user);
        return "redirect:/login?registered";
    }
}
