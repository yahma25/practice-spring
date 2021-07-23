package com.yahma.movie_review.repository.review;

import java.util.List;
import java.util.stream.IntStream;

import com.yahma.movie_review.entity.review.MemberForReview;
import com.yahma.movie_review.entity.review.Movie;
import com.yahma.movie_review.entity.review.Review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private MemberForReviewRepository memberForReviewRepository;

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

    /**
     * 세션 오류 발생
     * - org.hibernate.LazyInitializationException: could not initialize proxy - no Session
     * Review의 Member가 FetchType.LAZY 방식으로 되어 있음
     * '@Transactional' 적용해도 .getMemberForReview().getEmail() 처리할 때마다 객체 로딩 발생.
     * ReviewRepository.findByMovie()에서  @EntityGraph 방법으로 해결.
     */
    @Test
    public void testGetMovieReviews() {

        Movie movie = Movie.builder().mno(100L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview -> {
            System.out.println(movieReview.getReviewnum());
            System.out.println("\t" + movieReview.getGrade());
            System.out.println("\t" + movieReview.getText());
            System.out.println("\t" + movieReview.getMemberForReview().getEmail());
            System.out.println("---------------------------");
        });
    }

    @Test
    @Commit
    @Transactional
    public void testDeleteMemberForReview() {

        Long mid = 299L;

        MemberForReview memberForReview = MemberForReview.builder().mid(mid).build();

        // FK, 순서 문제로 오류 발생
        // memberForReviewRepository.deleteById(mid);
        // reviewRepository.deleteByMemberForReview(memberForReview);

        reviewRepository.deleteByMemberForReview(memberForReview);
        memberForReviewRepository.deleteById(mid);
    }
}
