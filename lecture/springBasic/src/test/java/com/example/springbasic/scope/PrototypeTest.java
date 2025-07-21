package com.example.springbasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

// 프로토타입 스코프 테스트
public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 프로토타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에 있기 때문에 @PreDestory가 동작하지 않는다
        //ac.close();

        // 직접 호출해야 함
        prototypeBean1.destory();
        prototypeBean2.destory();

    }

    // @Component가 없다? -> AnnotationConfigApplicationContext(PrototypeBean.class) 이렇게 지정해주면, PrototypeBean class 자체를 스캔하여 빈 등록을 하기 때문에 @Component 어노테이션이 필요없다
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init()");
        }

        @PreDestroy
        public void destory() {
            System.out.println("PrototypeBean.destory()");
        }
    }
}
