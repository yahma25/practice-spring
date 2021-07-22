package com.yahma.movie_review.repository.review;

import com.yahma.movie_review.entity.review.MemberForReview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberForReviewRepository extends JpaRepository<MemberForReview, Long> {
    
}
