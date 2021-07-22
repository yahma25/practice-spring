package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.MemberForReview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberForReviewRepository extends JpaRepository<MemberForReview, Long> {
    
}
