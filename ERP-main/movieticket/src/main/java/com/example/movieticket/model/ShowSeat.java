package com.example.movieticket.model;

import javax.persistence.*;

@Entity
@Table(name = "show_seats")
public class ShowSeat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;
    private boolean booked = false;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    public ShowSeat() {}
    // getters & setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getSeatNumber(){return seatNumber;}
    public void setSeatNumber(String seatNumber){this.seatNumber=seatNumber;}
    public boolean isBooked(){return booked;}
    public void setBooked(boolean booked){this.booked=booked;}
    public Show getShow(){return show;}
    public void setShow(Show show){this.show=show;}
}
