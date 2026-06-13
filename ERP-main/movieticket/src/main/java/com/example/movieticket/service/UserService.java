package com.example.movieticket.service;

import com.example.movieticket.model.User;
import com.example.movieticket.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public User register(User u){
        u.setPassword(encoder.encode(u.getPassword()));
        if (u.getRole()==null) u.setRole("USER");
        return userRepository.save(u);
    }

    public Optional<User> findByUsername(String username){ return userRepository.findByUsername(username); }
}
