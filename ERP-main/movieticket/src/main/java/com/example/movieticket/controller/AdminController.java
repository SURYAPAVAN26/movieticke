package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MovieRepository movieRepository;

    public AdminController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public String listMovies(Model model){
        model.addAttribute("movies", movieRepository.findAll());
        return "admin/movies";
    }

    @GetMapping("/movies/new")
    public String newMovieForm(Model model){
        model.addAttribute("movie", new Movie());
        return "admin/movie_form";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@ModelAttribute Movie movie){
        movieRepository.save(movie);
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model){
        model.addAttribute("movie", movieRepository.findById(id).orElse(new Movie()));
        return "admin/movie_form";
    }

    @PostMapping("/movies/delete")
    public String deleteMovie(@RequestParam Long id){
        movieRepository.deleteById(id);
        return "redirect:/admin/movies";
    }
}
