package com.yahma.movie_review.service.Movie;

import java.util.List;
import java.util.Map;

import com.yahma.movie_review.dto.movie.MovieDTO;
import com.yahma.movie_review.entity.review.Movie;
import com.yahma.movie_review.entity.review.MovieImage;
import com.yahma.movie_review.repository.review.MovieImageRepository;
import com.yahma.movie_review.repository.review.MovieRepository;

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
}
