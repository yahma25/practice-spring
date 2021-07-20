package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.MovieImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
    
}
