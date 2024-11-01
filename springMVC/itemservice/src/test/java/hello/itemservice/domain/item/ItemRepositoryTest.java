package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach // 테스트가 끝날 때 마다 실행되는 로직
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();
        Assertions.assertThat(items.size()).isEqualTo(2);
        Assertions.assertThat(items).contains(item1, item2);
    }

    @Test
    void updateItem() {
        Item item = new Item("itemA", 10000, 10);
        itemRepository.save(item);
        Long itemId = item.getId();

        Item updateItem = new Item("itemB", 20000, 20);
        itemRepository.update(itemId, updateItem);
        Assertions.assertThat(updateItem.getPrice()).isEqualTo(itemRepository.findById(itemId).getPrice());
    }
}
