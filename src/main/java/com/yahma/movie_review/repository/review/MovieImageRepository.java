package com.yahma.movie_review.repository.review;

import com.yahma.movie_review.entity.review.MovieImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
    
}
