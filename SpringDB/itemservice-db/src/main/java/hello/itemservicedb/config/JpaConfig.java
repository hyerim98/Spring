package hello.itemservicedb.config;

import hello.itemservicedb.repository.ItemRepository;
import hello.itemservicedb.repository.jpa.JpaItemRepository;
import hello.itemservicedb.repository.mybatis.ItemMapper;
import hello.itemservicedb.repository.mybatis.MyBatisItemRepository;
import hello.itemservicedb.service.ItemService;
import hello.itemservicedb.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "hello.itemservicedb.repository.jpa")
public class JpaConfig {

    private final EntityManager em;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(em);
    }
}
