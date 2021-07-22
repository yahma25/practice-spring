package com.yahma.movie_review.repository.review;

import com.yahma.movie_review.entity.review.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
