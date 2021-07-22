package com.yahma.movie_review.repository.review;

import java.util.List;

import com.yahma.movie_review.entity.review.Movie;
import com.yahma.movie_review.entity.review.Review;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    /**
     * attributePaths에 명시한 속성은 EAGER로 초리, 나머지는 LAZY로 처리.
     * Review를 처리할 때 MemberForReview 속성도 같이 로딩할 수 있돌고 함.
     */
    @EntityGraph(attributePaths = {"memberForReview"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
}
