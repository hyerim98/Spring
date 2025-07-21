package com.example.springbasic.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        // close()를 사용하기 위해 ConfigurableApplicationContext로 정의
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    // 빈 생명주기 콜백 테스트를 위해 수동 빈 생성
    @Configuration
    static class LifeCycleConfig {
        @Bean
        //@Bean(initMethod = "init", destroyMethod = "close") // 빈 등록 초기화, 소멸 메서드 지정하는 방법
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
