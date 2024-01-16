package com.example.springbasic.discount;

import com.example.springbasic.member.Grade;
import com.example.springbasic.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 자동으로 빈 등록을 위한 어노테이션
@Component
@Primary // 같은 타입 조회 빈이 2개 이상인 경우 해결 방법(rateDiscountPolicy, fixDiscountPolicy 2개의 빈이 조회되어서 rateDiscountPolicy가 우선순위를 가지도록)
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        }
        else {
            return 0;
        }
    }
}
