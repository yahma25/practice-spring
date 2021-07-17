package com.yahma.movie_review.service;

import com.yahma.movie_review.dto.ReplyDTO;
import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Reply;
import com.yahma.movie_review.repository.ReplyRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyRepository replyRepository;

  @Override
  public Long register(ReplyDTO replyDTO) {
    Reply reply = dtoToEntity(replyDTO);

    replyRepository.save(reply);

    return reply.getRno();
  }

  @Override
  public List<ReplyDTO> getList(Long bno) {
    List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(
      Board.builder().bno(bno).build()
    );

    return result
      .stream()
      .map(reply -> entityToDTO(reply))
      .collect(Collectors.toList());
  }

  @Override
  public void modify(ReplyDTO replyDTO) {
    Reply reply = dtoToEntity(replyDTO);

    replyRepository.save(reply);
  }

  @Override
  public void remove(Long rno) {
    replyRepository.deleteById(rno);
  }
}
