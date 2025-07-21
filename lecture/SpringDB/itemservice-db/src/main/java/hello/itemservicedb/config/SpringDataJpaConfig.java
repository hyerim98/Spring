package hello.itemservicedb.config;

import hello.itemservicedb.repository.ItemRepository;
import hello.itemservicedb.repository.jpa.JpaItemRepositoryV2;
import hello.itemservicedb.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservicedb.service.ItemService;
import hello.itemservicedb.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
//@MapperScan(basePackages = "hello.itemservicedb.repository.jpa")
public class SpringDataJpaConfig {

    private final SpringDataJpaItemRepository springDataJpaItemRepository;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV2(springDataJpaItemRepository);
    }
}
