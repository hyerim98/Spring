package com.example.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Member 저장, 조회

// 동시성 문제가 고려 되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }

    // new MemberRepository(); 이런식으로 생성 하지 못하게 하는 로직
    private MemberRepository() {}

    // member 저장
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // member id로 조회
    public Member findById(Member member) {
        return store.get(member.getId());
    }

    // member 전체 조회
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // store 비우기
    public void clearStore() {
        store.clear();
    }


}
