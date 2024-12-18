package hello.itemservicedb.config;

import hello.itemservicedb.repository.ItemRepository;
import hello.itemservicedb.repository.jpa.JpaItemRepositoryV2;
import hello.itemservicedb.repository.jpa.JpaItemRepositoryV3;
import hello.itemservicedb.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservicedb.repository.v2.ItemQueryRepositoryV2;
import hello.itemservicedb.repository.v2.ItemRepositoryV2;
import hello.itemservicedb.service.ItemService;
import hello.itemservicedb.service.ItemServiceV1;
import hello.itemservicedb.service.ItemServiceV2;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class V2Config {

    private final EntityManager em;
    private final ItemRepositoryV2 itemRepositoryV2; // SpringDataJPA

    @Bean
    public ItemService itemService() {
        return new ItemServiceV2(itemRepositoryV2, itemQueryRepositoryV2());
    }

    @Bean
    public ItemQueryRepositoryV2 itemQueryRepositoryV2() {
        return new ItemQueryRepositoryV2(em);
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }
}
