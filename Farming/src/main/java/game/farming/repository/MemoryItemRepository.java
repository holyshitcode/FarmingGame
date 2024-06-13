package game.farming.repository;

import game.farming.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static Map<Long,Item> items= new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        items.put(item.getId(),item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        return items.get(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(items.values());
    }
    @Override
    public Item update(Long id,Item updateParam) {
        Item findItem = findById(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setDescription(updateParam.getDescription());
        return findItem;
    }
    @Override
    public void delete(Long id) {
        items.remove(id);
    }
}
