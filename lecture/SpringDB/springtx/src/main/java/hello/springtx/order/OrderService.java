package hello.springtx.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRespository orderRespository;

    // JPA는 트랜잭션 커밋 시점에 Order 데이터를 DB에 반영
    @Transactional
    public void order(Order order) throws NotEnoughMoneyException {
        log.info("order 호출");
        orderRespository.save(order);

        log.info("결제 프로세스 진입");
        if (order.getUsername().equals("예외")) {
            log.info("시스템 예외 발생");
            throw new RuntimeException("시스템 예외");
        } else if (order.getUsername().equals("잔고부족")) {
            log.info("잔고 부족 비즈니스 예외 발생");
            order.setPayStatus("대기");
            // NotEnoughMoneyException은 시스템에 문제가 발생한 것이 아니라, 비즈니스 문제 상황을 예외를 통해 알려준다. 마치 예외가 리턴 값처럼 사용된다. 따라서 이 경우에는 트랜잭션을 커밋하는 것이 맞다.
            // 이 경우 롤백하면 생성한 Order 자체가 사라진다. 그러면 고객에게 잔고 부족을 알리고 별도의 계좌로 입금하도록 안내해도 주문(Order) 자체가 사라지기 때문에 문제가 된다.
            throw new NotEnoughMoneyException("잔고가 부족합니다"); // 롤백 하고 싶으면 체크 예외를 사용하지 않는다
        } else {
            // 정상 승인
            log.info("정상 승인");
            order.setPayStatus("완료");
        }

        log.info("결제 프로세스 완료");
    }
}
