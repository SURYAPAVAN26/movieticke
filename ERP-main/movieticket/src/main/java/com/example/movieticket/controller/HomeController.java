package com.example.movieticket.controller;

import com.example.movieticket.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final MovieService movieService;
    public HomeController(MovieService movieService){ this.movieService = movieService; }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("movies", movieService.activeMovies());
        return "index";
    }
}
