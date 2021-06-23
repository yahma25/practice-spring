package com.yahma.movie_review.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.yahma.movie_review.dto.SampleDTO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/")
@Log4j2
public class SampleController {
    
    @GetMapping("/movie")
    public void movieReview() {
        log.info("movie review ~ ~");
    }

    @GetMapping({"/movie2", "/movieLink", "/movieFormat"})
    public void movie2(Model model) {
        List<SampleDTO> list = IntStream.rangeClosed(1, 10).asLongStream().mapToObj(i -> {
            SampleDTO dto = SampleDTO.builder()
                .sno(i)
                .first("First.."+i)
                .last("Last.."+i)
                .regTime(LocalDateTime.now())
                .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping({"/movieInline"})
    public String movieLine(RedirectAttributes redirectAttributes) {
        log.info("movieInline............");

        SampleDTO dto = SampleDTO.builder()
            .sno(100L)
            .first("First..100")
            .last("Last..100")
            .regTime(LocalDateTime.now())
            .build();
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/movie3";
    }

    @GetMapping("/movie3")
    public void movie3() {
        log.info("movie3");
    }

    @GetMapping({"/layout/layout1", "/layout/layout2"})
    public void movieLayout() {
        log.info("movieLayout............");
    }
}
