package com.yahma.movie_review.controller.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
public class MovieController {
    
    @GetMapping("/register")
    public void register() {
        
    }
}
