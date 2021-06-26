package com.yahma.movie_review.service;

import com.yahma.movie_review.dto.GuestbookDTO;
import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.dto.PageResultDTO;
import com.yahma.movie_review.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
            .gno(dto.getGno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(dto.getWriter())
            .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
            .gno(entity.getGno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .writer(entity.getWriter())
            .regDate(entity.getRegDate())
            .modDate(entity.getModDate())
            .build();
        return dto;
    }
}
