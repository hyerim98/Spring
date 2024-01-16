package com.example.springbasic.autowired;

import com.example.springbasic.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

public class AutowiredTest { // outer class
    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    /*
        * inner class
            -> inner class는 outer class에 종속되어 있기 때문에 outer class의 객체를 통해서만 inner class에 접근을 할 수 있다
            -> inner class를 독립적으로 사용되게 만들고 싶다면 static을 붙이면 된다
            * 이러한 이유로 AppConfig class는 static이 아니어도 test 코드에서 사용할 수 있던것이었고, TestBean class는 static이 있어야만 테스트 할 수 있는 것이다
                *   참고 : https://www.inflearn.com/questions/257297/testconfig-%ED%81%B4%EB%9E%98%EC%8A%A4%EC%97%90%EC%84%9C-static%EC%9D%84-%EB%96%BC%EB%B2%84%EB%A6%AC%EB%A9%B4
    */
    @Configuration // 현재 코드에서는 필요가 없긴 함 -> 스프링 컨텍스트에 빈을 등록하여 테스트하는 작업이 아니라 의존관계 주입이 안되는 경우 어떠한 상태로 출력되는지만 확인하는 테스트 용도이기 때문
    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }

}
