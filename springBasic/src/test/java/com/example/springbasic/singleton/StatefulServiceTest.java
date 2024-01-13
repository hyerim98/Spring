package com.example.springbasic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // statefulService1 == statefulService2 (싱글톤 컨테이너로 인하여 2개의 객체가 같음 -> 상태를 유지하는 필드가 있으면 위험)
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // statefulService1 == statefulService2 (싱글톤 컨테이너로 인해 2개의 객체가 같지만, 상태를 유지하는 필드가 없기 때문에 안전)
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        // ThreadA : A사용자 10000원 주문
        int userAPrice = statelessService1.order("userA", 10000);
        // ThreadB : B사용자 20000원 주문
        int userBPrice = statelessService2.order("userB", 20000);
        System.out.println("userAPrice = " + userAPrice);

        assertThat(userAPrice).isEqualTo(10000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

}