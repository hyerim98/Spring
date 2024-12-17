package hello.itemservicedb.repository.jpa;

import hello.itemservicedb.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {
    // 이름 조건만 검색했을 때 사용하는 쿼리 메서드
    List<Item> findByItemNameLike(String itemName);
    // 가격 조건만 검색했을 때 사용하는 쿼리 메서드
    List<Item> findByPriceLessThanEqual(Integer price);

    // 쿼리 메서드(아래 메서드와 같은 기능 수행)
    // 가격 조건만 검색했을 대 사용하는 쿼리 메서드
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

    // 쿼리 직접 실행
    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(String itemName, Integer price);
}
