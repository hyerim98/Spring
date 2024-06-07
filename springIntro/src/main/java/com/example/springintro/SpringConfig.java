package com.example.springintro;

import com.example.springintro.aop.TimeTraceAop;
import com.example.springintro.repository.*;
import com.example.springintro.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    // JDBC 사용 시 필요
    /*private DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    // JPA 사용 시 필요
    /*private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    // 스프링 데이터 JPA 사용 시 필요
    // 스프링 데이터 JPA가 SpringDataJpaMemberRepository를 보고 스프링 빈을 자동으로 만든다
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/

    //AOP
    // SpringConfig.java 에서 빈으로 등록 OR @Component를 사용
    /*@Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }*/
}
