package hello.itemservice;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ItemserviceApplication {

    private final ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(ItemserviceApplication.class, args);
    }

    /**
     * 테스트용(미리 데이터 넣어놓기)
     */
    @PostConstruct
    public void init() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
    }

}
