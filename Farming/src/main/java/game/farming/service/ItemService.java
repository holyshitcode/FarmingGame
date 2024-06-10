package game.farming.service;

import game.farming.domain.Item;
import game.farming.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }
    public Item findById(Long id) {
        return itemRepository.findById(id);
    }
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    public Item update(Long id,Item item) {
        return itemRepository.update(id,item);
    }
    public void delete(Long id) {
        itemRepository.delete(id);
    }
}
