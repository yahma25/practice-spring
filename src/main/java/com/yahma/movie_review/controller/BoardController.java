package com.yahma.movie_review.controller;

import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.service.BoardService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list....." + pageRequestDTO);

        model.addAttribute("result", boardService.getList(pageRequestDTO));
    }
}
