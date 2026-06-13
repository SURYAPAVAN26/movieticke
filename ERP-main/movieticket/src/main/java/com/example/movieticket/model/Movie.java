package com.example.movieticket.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private int duration; // minutes
    @Column(length = 2000)
    private String description;
    private String posterUrl;
    private boolean active = true;

    public Movie() {}
    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre=genre;}
    public int getDuration(){return duration;}
    public void setDuration(int duration){this.duration=duration;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}
    public String getPosterUrl(){return posterUrl;}
    public void setPosterUrl(String posterUrl){this.posterUrl=posterUrl;}
    public boolean isActive(){return active;}
    public void setActive(boolean active){this.active=active;}
}
