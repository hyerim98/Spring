package com.example.springbasic.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

// 어노테이션 직접 생성
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    /* 사용 예시) 조회 빈이 2개 이상 나올 경우 @Qualifier 사용 하여 해결 하는 것 대신 자신이 직접 생성한 어노테이션으로 문제 해결
    * RateDiscountPolicy.java
      @Component
      @MainDiscountPolicy
      public class RateDiscountPolicy implements DiscountPolicy {}

    * OrderServiceImpl.java
      //생성자 자동 주입
      @Autowired
      public OrderServiceImpl(MemberRepository memberRepository,
         @MainDiscountPolicy DiscountPolicy discountPolicy) {
         this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;
      }

      //수정자 자동 주입
      @Autowired
      public DiscountPolicy setDiscountPolicy(@MainDiscountPolicy DiscountPolicy
        discountPolicy) {
         this.discountPolicy = discountPolicy;
      }
    */
}
