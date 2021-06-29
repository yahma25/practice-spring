package com.yahma.movie_review.repository;

import java.util.List;

import com.yahma.movie_review.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 한 개의 로우(Object) 내에 Object[]
    @Query("select b, w from Board b left join b.writer w where b.bno =:bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("select b, r from Board b left join Reply r on r.board = b where b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "select b, w, count(r)" +
            " from Board b" + 
            " left join b.writer w" +
            " left join Reply r on r.board = b" +
            " group by b",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("select b, w, count(r)" +
            " from Board b left join b.writer w " +
            " left outer join Reply r on r.board = b" +
            " where b.bno =:bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
