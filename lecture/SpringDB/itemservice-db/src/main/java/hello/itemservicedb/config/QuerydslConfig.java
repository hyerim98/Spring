package hello.itemservicedb.config;

import hello.itemservicedb.repository.ItemRepository;
import hello.itemservicedb.repository.jpa.JpaItemRepositoryV2;
import hello.itemservicedb.repository.jpa.JpaItemRepositoryV3;
import hello.itemservicedb.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservicedb.service.ItemService;
import hello.itemservicedb.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {

    private final EntityManager em;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }
}
