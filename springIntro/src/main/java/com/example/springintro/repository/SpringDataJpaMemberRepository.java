package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA
// JpaRepository를 받고 있으면 구현체를 자동으로 만들어줌(스프링 빈에 자동으로 등록이 됨)
// 내가 스프링 빈에 등록하는 게 아니라 스프링 데이터 JPA가 구현체를 만들어 등록해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 해당 쿼리는 JpaRepository에 없는 것이다
    // JPQL(findBy~~ 메소드 이름만으로도 조회 기능 제공) "select * from member where name = ?" : 인터페이스 이름만으로도 쿼리 완료
    @Override
    Optional<Member> findByName(String name);
}
