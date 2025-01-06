package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
public class TxBasicTest {
    // 실제 basicService 객체 대신에 프록시인 basicService$$CGLIB를 스프링 빈에 등록, 그리고 프록시는 내부에 실제 basicService를 참조
    @Autowired BasicService basicService;

    // proxy가 적용되었는지 확인
    @Test
    void proxyCheck() {
        log.info("aop class = {}", basicService.getClass());
        // 선언적 트랜잭션 방식에서 스프링 트랜잭션은 AOP를 기반으로 동작
        assertThat(AopUtils.isAopProxy(basicService)).isTrue();
    }

    @Test
    void txTest() {
        basicService.tx();
        basicService.nonTx();
    }

    @TestConfiguration
    static class TxAppltBasicConfig {
        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Slf4j
    static class BasicService {

        // @Transactional을 메서드나 클래스에 붙이면 해당 객체는 트랜잭션 AOP 적용의 대상이 되고, 결과적으로 실제 객체 대신에 트랜잭션을 처리해주는 프록시 객체가 스프링 빈에 등록 됨
        @Transactional // 트랜잭션 AOP는 프록시를 만들어서 스프링 컨테이너에 등록함(실제 객체 대신에 트랜잭션을 처리해주는 프록시 객체가 스프링 빈에 등록 됨)
        public void tx() {
            log.info("call tx");
            // 트랜잭션 적용 여부 확인
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active = {}", txActive);
        }

        public void nonTx() {
            log.info("call nonTx");
            // 트랜잭션 적용 여부 확인
            boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
            log.info("nonTx active = {}", txActive);
        }
    }


}
