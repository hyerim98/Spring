package com.example.springbasic.member;

// 회원 서비스(가입, 조회) 인터페이스 -> 해당 서비스를 사용하여 회원 가입, 조회 진행
public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);
}
