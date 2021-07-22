package com.yahma.movie_review.repository.review;

import com.yahma.movie_review.entity.review.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}
