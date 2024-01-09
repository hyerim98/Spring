package com.example.springbasic.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 자동으로 빈 등록을 위한 어노테이션
@Component
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
