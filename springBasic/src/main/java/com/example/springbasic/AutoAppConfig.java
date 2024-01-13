package com.example.springbasic;

import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
// 스프링 컨테이너 생성 -> 스프링 컨테이너에 빈 등록
/* 컴포넌트 스캔으로 인해 자동으로 아래와 같은 코드처럼 설정됨
* @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
*/

// 스프링 설정
@Configuration
// 설정 정보가 없어도 자동으로 스프링 빈을 등록(@Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록)
@ComponentScan(
        // 제외 시킬 것(기존 예제 코드 남기기 위해)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 수동 빈 VS 자동 빈 테스트 용도(수동 빈이 우선권이지만, 최근 스프링 부트는 충돌나면 오류가 발생하도록 기본 값을 바꿈)
    // 원래는 class안에 아무것도 없어도됨(테스트를 위해 아래 메서드 추가한 것! 수동 빈 VS 자동 빈 테스트가 아니라면 지워도 됨)
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
