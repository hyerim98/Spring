package com.example.springintro.repository;

import com.example.springintro.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // DB Sequence 역할

    @Override
    public Member save(Member member) { // member 이름이 넘어오는 상태
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // NULL이어도 감싸서 반환 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store 안에 있는 값에서 name과 같은 것이 있다면 member 반환, 없다면 Optional 감싸서 null 반환
        return store.values().stream().filter(e -> e.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
