package com.yahma.movie_review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/")
@Log4j2
public class SampleController {
    
    @GetMapping("/movie")
    public void movieReview() {
        log.info("movie review ~ ~");
    }
}
