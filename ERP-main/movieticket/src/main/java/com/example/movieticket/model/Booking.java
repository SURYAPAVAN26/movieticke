package com.example.movieticket.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingRef;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<ShowSeat> seats;

    private double totalPrice;
    private LocalDateTime bookingTime;
    private String status; // BOOKED, CANCELLED, REFUNDED

    public Booking() {}
    // getters & setters (omitted for brevity - include all in your code)
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getBookingRef(){return bookingRef;}
    public void setBookingRef(String bookingRef){this.bookingRef=bookingRef;}
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}
    public Show getShow(){return show;}
    public void setShow(Show show){this.show=show;}
    public List<ShowSeat> getSeats(){return seats;}
    public void setSeats(List<ShowSeat> seats){this.seats=seats;}
    public double getTotalPrice(){return totalPrice;}
    public void setTotalPrice(double totalPrice){this.totalPrice=totalPrice;}
    public LocalDateTime getBookingTime(){return bookingTime;}
    public void setBookingTime(LocalDateTime bookingTime){this.bookingTime=bookingTime;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
