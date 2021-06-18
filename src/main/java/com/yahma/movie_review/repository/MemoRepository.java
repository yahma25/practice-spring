package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.Memo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    
}