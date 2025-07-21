package com.example.springbasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

// 프로토타입 스코프 - 싱글톤 빈과 프로토타입 빈 함께 사용시 문제점 테스트

public class SingletonWithPrototypeTest {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    // 기본 - 싱글톤 스코프
    @RequiredArgsConstructor
    static class ClientBean {
        // 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점 테스트 시 필요한 필드
        //private final PrototypeBean prototypeBean; // 생성 시점에 주입

        // ObjectProvider : 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결 테스트를 위해 필요한 필드
        //private final ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // Provider : 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 Provider로 문제 해결 테스트를 위해 필요한 필드
        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // ObjectProvider getObject() : 스프링 컨테이너에서 프로토타입 빈을 찾아서 반환
            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject();

            // Provider get() : 스프링 컨테이너에서 프로토타입 빈을 찾아서 반환
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    // 프로토타입 스코프
    @Scope("prototype")
    @Getter
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init() " + this);
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory() " + this);
        }

    }
}
