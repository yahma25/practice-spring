package com.yahma.movie_review.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import com.yahma.movie_review.dto.BoardDTO;
import com.yahma.movie_review.dto.PageRequestDTO;
import com.yahma.movie_review.dto.PageResultDTO;
import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Member;
import com.yahma.movie_review.repository.BoardRepository;
import com.yahma.movie_review.repository.ReplyRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {
    private final BoardRepository repository;

    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {
        log.info(dto);

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDTO((Board)en[0], (Member)en[1], (Long)en[2]));

        Page<Object[]> result = repository.getBoardWithReplyCount(
            pageRequestDTO.getPageable(Sort.by("bno").descending())
        );

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = repository.getBoardByBno(bno);

        Object[] arr = (Object[])result;

        return entityToDTO((Board)arr[0], (Member)arr[1], (Long)arr[2]);
    }

    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);

        repository.deleteById(bno);
    }

    @Transactional
    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = repository.getById(boardDTO.getBno());

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        repository.save(board);
    }
}
