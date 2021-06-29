package com.yahma.movie_review.service;

import com.yahma.movie_review.dto.BoardDTO;
import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }
}
