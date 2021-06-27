package com.yahma.movie_review.controller;

import com.yahma.movie_review.dto.GuestbookDTO;
import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.service.GuestbookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public void register() {
        log.info("register get.....");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto...", dto);

        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("gno: " + gno);

        GuestbookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        log.info("post modify................");
        log.info("dto : " + dto);

        service.modify(dto);
        
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("gno", dto.getGno());

        return "redirect:/guestbook/read";
    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {
        log.info("gno: " + gno);

        service.remove(gno);
        redirectAttributes.addFlashAttribute("msg", gno);
        
        return "redirect:/guestbook/list";
    }
}
