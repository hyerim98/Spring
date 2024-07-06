package com.example.springbasic.singleton;

import com.example.springbasic.AppConfig;
import com.example.springbasic.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        System.out.println(memberService1 == memberService2);
        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // same : == (인스턴스 참조 값 비교)
        assertThat(singletonService1).isSameAs(singletonService2);

        SingletonService.getInstance().logic();
    }

    // 스프링 빈이 싱글톤이 되도록 보장
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = appConfig.getBean("memberService", MemberService.class);
        MemberService memberService2 = appConfig.getBean("memberService", MemberService.class);

        // 참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        System.out.println(memberService1 == memberService2);
        // memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
