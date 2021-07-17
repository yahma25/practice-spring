package com.yahma.movie_review.service;

import com.yahma.movie_review.dto.ReplyDTO;
import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Reply;
import java.util.List;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);

  List<ReplyDTO> getList(Long bno);

  void modify(ReplyDTO replyDTO);

  void remove(Long rno);

  default Reply dtoToEntity(ReplyDTO replyDTO) {
    Board board = Board.builder().bno(replyDTO.getBno()).build();

    Reply reply = Reply
      .builder()
      .rno(replyDTO.getRno())
      .text(replyDTO.getText())
      .replier(replyDTO.getReplier())
      .board(board)
      .build();

    return reply;
  }

  default ReplyDTO entityToDTO(Reply reply) {
    ReplyDTO dto = ReplyDTO
      .builder()
      .rno(reply.getRno())
      .text(reply.getText())
      .replier(reply.getReplier())
      .regDate(reply.getRegDate())
      .modDate(reply.getModDate())
      .build();

    return dto;
  }
}
