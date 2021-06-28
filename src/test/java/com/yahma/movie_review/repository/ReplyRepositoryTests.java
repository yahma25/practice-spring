package com.yahma.movie_review.repository;

import java.util.stream.IntStream;

import com.yahma.movie_review.entity.Board;
import com.yahma.movie_review.entity.Reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                .text("Reply..." + i)
                .board(board)
                .replier("guest")
                .build();
            replyRepository.save(reply);
        });
    }
}
