package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.Reply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
}
