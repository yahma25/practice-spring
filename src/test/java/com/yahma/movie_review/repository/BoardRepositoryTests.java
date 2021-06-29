package com.yahma.movie_review.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                .title("Title.." + i)
                .content("Content.." + i)
                .writer(member)
                .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }
}
