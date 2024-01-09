package com.example.springbasic;

import com.example.springbasic.member.MemberRepository;
import com.example.springbasic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 스프링 설정
@Configuration
// 설정 정보가 없어도 자동으로 스프링 빈을 등록(@Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록)
@ComponentScan(
        // 제외 시킬 것(기존 예제 코드 남기기 위해)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 수동 빈 VS 자동 빈 테스트 용도(수동 빈이 우선권이지만, 최근 스프링 부트는 충돌나면 오류가 발생하도록 기본 값을 바꿈)
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
