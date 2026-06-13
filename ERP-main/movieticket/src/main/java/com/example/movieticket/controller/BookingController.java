package com.example.movieticket.controller;

import com.example.movieticket.model.Booking;
import com.example.movieticket.model.User;
import com.example.movieticket.repository.UserRepository;
import com.example.movieticket.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;
    private final UserRepository userRepository;

    public BookingController(BookingService bookingService, UserRepository userRepository){
        this.bookingService = bookingService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public String createBooking(@RequestParam Long showId, @RequestParam(name="seatIds", required=false) String seatIdsCsv, Authentication authentication, Model model){
        if (seatIdsCsv == null || seatIdsCsv.trim().isEmpty()) {
            return "redirect:/movies/show/" + showId + "/seats?error=No+seats+selected";
        }
        try {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
            List<Long> seatIds = Arrays.stream(seatIdsCsv.split(","))
                                       .map(String::trim)
                                       .filter(s -> !s.isEmpty())
                                       .map(Long::valueOf)
                                       .collect(Collectors.toList());
            if (seatIds.isEmpty()) {
                return "redirect:/movies/show/" + showId + "/seats?error=No+seats+selected";
            }
            Booking b = bookingService.createBooking(user.getId(), showId, seatIds);
            model.addAttribute("booking", b);
            return "booking_confirm";
        } catch(Exception ex){
            String errorMsg = ex.getMessage() != null ? ex.getMessage() : "Unknown error";
            try {
                errorMsg = java.net.URLEncoder.encode(errorMsg, "UTF-8");
            } catch (Exception e) {}
            return "redirect:/movies/show/" + showId + "/seats?error=" + errorMsg;
        }
    }

    @GetMapping({"", "/my"})
    public String myBookings(Authentication authentication, Model model){
        try {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new RuntimeException("User not found"));
            model.addAttribute("bookings", bookingService.bookingsForUser(user.getId()));
            return "bookings";
        } catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "bookings";
        }
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam Long bookingId, Authentication authentication, Model model){
        try {
            Booking b = bookingService.cancelBooking(bookingId);
            model.addAttribute("bookings", bookingService.bookingsForUser(b.getUser().getId()));
            return "bookings";
        } catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "bookings";
        }
    }
}
