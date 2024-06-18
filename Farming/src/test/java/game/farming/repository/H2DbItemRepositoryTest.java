package game.farming.repository;

import game.farming.domain.Item;
import game.farming.domain.Member;
import game.farming.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class H2DbItemRepositoryTest {
    @Autowired
    private ItemService itemService;

    @Test
    void save() {
        Item item = new Item("itemA","newitem",10000);
        itemService.save(item);
        Item foundItem = itemService.findById(item.getId());

        assertThat(foundItem).isEqualTo(item);
    }

    @Test
    void findById() {
        Item item = new Item("itemA","newitem",10000);
        itemService.save(item);
        Optional<Item> foundItem = Optional.ofNullable(itemService.findById(item.getId()));
        assertThat(foundItem.isPresent()).isTrue();
    }

    @Test
    void findAll() {
        Item item = new Item("itemA","newitem",10000);
        Item item1 = new Item("itemB","newitems",100001);
        Item item2 = new Item("itemC","newitemss",100002);
        itemService.save(item);
        itemService.save(item1);
        itemService.save(item2);
        List<Item> foundItems = itemService.findAll();
        assertThat(foundItems.size()).isEqualTo(3);

    }

    @Test
    void update() {
        Item item = new Item("itemA","newitem",10000);
        itemService.save(item);
        item.setItemName("itemB");
        itemService.update(item.getId(), item);
        assertThat(item.getItemName()).isEqualTo("itemB");

    }

    @Test
    void delete() {
        Item item = new Item("itemA","newitem",10000);
        itemService.save(item);
        itemService.delete(item.getId());
        assertThat(itemService.findById(item.getId())).isNull();
    }
}