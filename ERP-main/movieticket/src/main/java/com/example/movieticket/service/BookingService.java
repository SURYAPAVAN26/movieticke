package com.example.movieticket.service;

import com.example.movieticket.model.*;
import com.example.movieticket.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowSeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, ShowSeatRepository seatRepository, ShowRepository showRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Booking createBooking(Long userId, Long showId, List<Long> seatIds) throws Exception {
        List<ShowSeat> seats = seatRepository.lockSeatsByIds(seatIds);
        for(ShowSeat s: seats){
            if(s.isBooked()){
                throw new Exception("Seat "+s.getSeatNumber()+" already booked");
            }
        }
        for(ShowSeat s: seats){
            s.setBooked(true);
            seatRepository.save(s);
        }
        Show show = showRepository.findById(showId).orElseThrow(() -> new Exception("Invalid show"));
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("Invalid user"));

        Booking b = new Booking();
        b.setBookingRef(UUID.randomUUID().toString().substring(0,8).toUpperCase());
        b.setUser(user);
        b.setShow(show);
        b.setSeats(seats);
        b.setTotalPrice(show.getPrice() * seats.size());
        b.setBookingTime(LocalDateTime.now());
        b.setStatus("BOOKED");
        return bookingRepository.save(b);
    }

    @Transactional
    public Booking cancelBooking(Long bookingId) throws Exception {
        Booking b = bookingRepository.findById(bookingId).orElseThrow(()-> new Exception("Invalid booking"));
        if("CANCELLED".equals(b.getStatus()) || "REFUNDED".equals(b.getStatus())){
            throw new Exception("Already cancelled/refunded");
        }
        b.setStatus("CANCELLED");
        bookingRepository.save(b);
        for(ShowSeat s: b.getSeats()){
            s.setBooked(false);
            seatRepository.save(s);
        }
        b.setStatus("REFUNDED");
        return bookingRepository.save(b);
    }

    public List<Booking> bookingsForUser(Long userId){
        return bookingRepository.findByUserId(userId);
    }
}
