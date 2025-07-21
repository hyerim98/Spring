package hello.itemservicedb;

import hello.itemservicedb.domain.Item;
import hello.itemservicedb.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가
     * @PostConstruct : @Transactional과 관련된 AOP가 적용되지 않은 상태로 호출될 수 도 있다
     * @EventListener : AOP를 포함한 스프링 컨테이너가 완전히 초기화 된 이후에 호출됨
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
