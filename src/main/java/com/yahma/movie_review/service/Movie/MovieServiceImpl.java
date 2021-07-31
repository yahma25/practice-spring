package com.yahma.movie_review.service.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.dto.PageResultDTO;
import com.yahma.movie_review.dto.movie.MovieDTO;
import com.yahma.movie_review.entity.review.Movie;
import com.yahma.movie_review.entity.review.MovieImage;
import com.yahma.movie_review.repository.review.MovieImageRepository;
import com.yahma.movie_review.repository.review.MovieRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    
    private final MovieRepository movieRepository;

    private final MovieImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(MovieDTO movieDTO) {
        
        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });
        
        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
            (Movie) arr[0],
            (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])),
            (Double) arr[2],
            (Long) arr[3]
        ));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public MovieDTO getMovie(Long mno) {
        
        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ result ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        log.info("result 0번째: ", result.get(0));

        // Movie Entity는 가장 앞에 존재 - 모든 Row가 같은 값
        Movie movie = (Movie) result.get(0)[0];
        log.info("movie", movie);

        // 영화의 이미지 개수만큼 객체 필요
        List<MovieImage> movieImages = new ArrayList<>();

        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImages.add(movieImage);
        });

        Double avg = (Double) result.get(0)[2]; // 평균 평점
        Long reviewCount = (Long) result.get(0)[3]; // 리뷰 개수

        return entitiesToDTO(movie, movieImages, avg, reviewCount);
    }
}
