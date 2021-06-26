package com.yahma.movie_review.controller;

import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.service.GuestbookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor // 자동 주입
public class GuestbookController {
    private final GuestbookService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/guestbook/list";
    }
    
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list.........." + pageRequestDTO);
        
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
