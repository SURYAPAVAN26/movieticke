package com.example.movieticket.model;

import javax.persistence.*;

@Entity
@Table(name = "theaters")
public class Theater {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    public Theater() {}
    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getLocation(){return location;}
    public void setLocation(String location){this.location=location;}
}
