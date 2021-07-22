package com.yahma.movie_review.repository.review;

import java.util.stream.IntStream;

import com.yahma.movie_review.entity.review.MemberForReview;
import com.yahma.movie_review.repository.review.MemberForReviewRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberForReviewRepositoryTests {
    @Autowired
    private MemberForReviewRepository memberForReviewRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            MemberForReview memberForReview = MemberForReview.builder()
                    .email("r" + i + "@naver.com")
                    .pw("1111")
                    .nickname("reviewer" + i)
                    .build();

            memberForReviewRepository.save(memberForReview);
        });
    }
}
