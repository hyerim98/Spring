package com.example.springbasic.discount;

import com.example.springbasic.member.Member;

// 할인 정책 인터페이스(해당 인터페이스는 서비스 로직에서 직접적으로 사용 할 일이 없으므로 서비스 X -> 주문 서비스에 의존 관계 주입되어서 사용됨)

public interface DiscountPolicy {
    /* @return 할인 대상 금액 */
    int discount(Member member, int price);
}
