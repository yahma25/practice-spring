package com.yahma.movie_review.repository;

import com.yahma.movie_review.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
