package com.example.movieticket.repository;

import com.example.movieticket.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {}
