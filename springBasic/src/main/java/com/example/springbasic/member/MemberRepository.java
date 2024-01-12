package com.example.springbasic.member;

// 회원 가입, 조회 인터페이스(해당 인터페이스는 서비스 로직에서 직접적으로 사용 할 일이 없으므로 서비스 X -> 회원 서비스에 의존 관계 주입되어서 사용됨)
public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
