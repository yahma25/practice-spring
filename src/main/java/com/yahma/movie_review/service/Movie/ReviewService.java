package com.yahma.movie_review.service.Movie;

import java.util.List;

import com.yahma.movie_review.dto.movie.ReviewDTO;
import com.yahma.movie_review.entity.movie.Review;
import com.yahma.movie_review.entity.review.MemberForReview;
import com.yahma.movie_review.entity.review.Movie;

public interface ReviewService {
    
    // 영화의 모든 영화 리뷰 가져오기
    List<ReviewDTO> getListOfMovie(Long mno);

    // 영화 리뷰를 추가
    Long register(ReviewDTO reviewDTO);

    // 특정 영화 리뷰 숮어
    void modify(ReviewDTO reviewDTO);

    // 영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO reviewDTO) {

        Review review = Review.builder()
                .reviewnum(reviewDTO.getReviewnum())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .memberForReview(MemberForReview.builder().mid(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();

        return review;
    }

    default ReviewDTO entityToDto(Review review) {
        
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewnum(review.getReviewnum())
                .mno(review.getMovie().getMno())
                .mid(review.getMemberForReview().getMid())
                .nickname(review.getMemberForReview().getNickname())
                .email(review.getMemberForReview().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }
}
