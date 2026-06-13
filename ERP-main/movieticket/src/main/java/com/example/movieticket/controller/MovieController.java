package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Show;
import com.example.movieticket.model.ShowSeat;
import com.example.movieticket.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public String movieDetails(@PathVariable Long id, Model model){
        Movie movie = movieService.getMovie(id);
        if (movie == null) return "redirect:/";
        List<Show> shows = movieService.showsForMovie(id);
        model.addAttribute("movie", movie);
        model.addAttribute("shows", shows);
        return "movie_details";
    }

    @GetMapping("/show/{showId}/seats")
    public String seatSelection(@PathVariable Long showId, Model model){
        Show show = movieService.getShow(showId);
        if(show==null) return "redirect:/";
        movieService.ensureSeatsForShow(show);
        List<ShowSeat> seats = movieService.seatsForShow(showId);
        model.addAttribute("show", show);
        model.addAttribute("seats", seats);
        return "seat_selection";
    }
}
