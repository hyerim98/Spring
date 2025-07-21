package com.example.springintro.repository;

import com.example.springintro.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    // Optional : 가져오는 값이 null이 되어도 감싸서 반환 가능
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll();
}
