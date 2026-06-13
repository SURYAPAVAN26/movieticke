package com.example.movieticket.service;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Show;
import com.example.movieticket.model.ShowSeat;
import com.example.movieticket.repository.MovieRepository;
import com.example.movieticket.repository.ShowRepository;
import com.example.movieticket.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository seatRepository;

    public MovieService(MovieRepository movieRepository, ShowRepository showRepository, ShowSeatRepository seatRepository){
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
    }

    public List<Movie> activeMovies(){ return movieRepository.findByActiveTrue(); }
    public Movie save(Movie m){ return movieRepository.save(m); }
    public Movie getMovie(Long id){ return movieRepository.findById(id).orElse(null); }
    public List<Show> showsForMovie(Long movieId){ return showRepository.findByMovieId(movieId); }
    public Show getShow(Long id){ return showRepository.findById(id).orElse(null); }
    public List<ShowSeat> seatsForShow(Long showId){ return seatRepository.findByShowId(showId); }

    @Transactional
    public void ensureSeatsForShow(Show s){
        List<ShowSeat> seats = seatRepository.findByShowId(s.getId());
        if (seats == null || seats.isEmpty()){
            char[] rows = new char[]{'A','B','C','D'};
            for(char r: rows){
                for(int c=1;c<=8;c++){
                    ShowSeat ss = new ShowSeat();
                    ss.setSeatNumber(""+r+c);
                    ss.setShow(s);
                    ss.setBooked(false);
                    seatRepository.save(ss);
                }
            }
        }
    }
}
