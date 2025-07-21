package hello.itemservicedb.repository.v2;

import hello.itemservicedb.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// 기본적 CRUD
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
