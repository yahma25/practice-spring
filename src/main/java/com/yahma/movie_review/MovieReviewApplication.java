package com.yahma.movie_review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.yahma.entity"})
public class MovieReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieReviewApplication.class, args);
    }

}
