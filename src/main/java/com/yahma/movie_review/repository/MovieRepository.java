package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
