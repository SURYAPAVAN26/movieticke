package com.example.movieticket.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column(name = "show_time")
    private LocalDateTime showTime;

    private double price;

    public Show() {}
    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Movie getMovie(){return movie;}
    public void setMovie(Movie movie){this.movie=movie;}
    public Theater getTheater(){return theater;}
    public void setTheater(Theater theater){this.theater=theater;}
    public LocalDateTime getShowTime(){return showTime;}
    public void setShowTime(LocalDateTime showTime){this.showTime=showTime;}
    public double getPrice(){return price;}
    public void setPrice(double price){this.price=price;}
}
