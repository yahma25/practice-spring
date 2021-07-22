package com.yahma.movie_review.repository.review;

import java.util.stream.IntStream;

import com.yahma.movie_review.entity.review.MemberForReview;
import com.yahma.movie_review.entity.review.Movie;
import com.yahma.movie_review.entity.review.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            Long mno = (long)(Math.random() * 100) + 1;

            Long mid = (long)(Math.random() * 100) + 1;
            MemberForReview memberForReview = MemberForReview.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .memberForReview(memberForReview)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random() * 5) + 1)
                    .text("제 평가는요..." + i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }
}
