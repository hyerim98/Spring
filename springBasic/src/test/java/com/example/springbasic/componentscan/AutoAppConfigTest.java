package com.example.springbasic.componentscan;

import com.example.springbasic.AutoAppConfig;
import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.RateDiscountPolicy;
import com.example.springbasic.member.Grade;
import com.example.springbasic.member.Member;
import com.example.springbasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

// 스프링 빈 자동 생성(컴포넌트 스캔) 테스트
public class AutoAppConfigTest {
    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discount = rateDiscountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(1000);
    }
}
