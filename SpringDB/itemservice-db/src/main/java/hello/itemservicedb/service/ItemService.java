package hello.itemservicedb.service;

import hello.itemservicedb.domain.Item;
import hello.itemservicedb.repository.ItemSearchCond;
import hello.itemservicedb.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);
}
