package com.example.springbasic.order;

import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.FixDiscountPolicy;
import com.example.springbasic.discount.RateDiscountPolicy;
import com.example.springbasic.member.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component // 자동으로 빈 등록을 위한 어노테이션
@Getter // 싱글톤 테스트 용도
@RequiredArgsConstructor // 생성자 주입 자동으로 해주는 어노테이션
public class OrderServiceImpl implements OrderService{

    // 의존관계 생성자 주입 시 final 키워드 반드시 필요
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입 : 자동으로 의존관계 주입(생성자 1개 시에는 @Autowired 생략해도 자동으로 주입 됨)
    /* == @RequiredArgsConstructor과 같은 역할
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
