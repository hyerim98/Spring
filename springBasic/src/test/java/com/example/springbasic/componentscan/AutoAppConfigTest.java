package com.example.springbasic.componentscan;

import com.example.springbasic.AutoAppConfig;
import com.example.springbasic.discount.DiscountPolicy;
import com.example.springbasic.discount.RateDiscountPolicy;
import com.example.springbasic.member.Grade;
import com.example.springbasic.member.Member;
import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemberService;
import com.example.springbasic.order.OrderServiceImpl;
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

        // MemberService (컴포넌트 스캔 테스트)
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        // DiscountPolicy (컴포넌트 스캔 테스트)
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discount = rateDiscountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(1000);


        // 조회 빈이 2개 이상일 경우의 테스트
        /*
        * @Autowired, @RequiredArgsConstructor 는 타입(Type)으로 조회
            - ac.getBean(DiscountPolicy.class) 해당 코드처럼 실행 되어 같은 타입 조회 대상 빈이 2개 이상일 때 오류가 발생(rateDiscountPolicy, fixDiscountPolicy)
        * 해당 문제 해결 방법
            * OrderServiceImpl.java 클래스에서 private final DiscountPolicy discountPolicy; -> private final DiscountPolicy rateDiscountPolicy;로 변경
            * @Qualifier 이용
            * RateDiscountPolicy.java(사용할 클래스) 에다 @Primary 어노테이션 붙인다
        */
        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        DiscountPolicy discountPolicy = bean.getDiscountPolicy();
        System.out.println("discountPolicy = " + discountPolicy);
    }
}
