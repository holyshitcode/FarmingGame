package game.farming.repository;

import game.farming.domain.Item;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface ItemRepository {

    public Item save(Item item);
    public Item findById(Long id);
    public List<Item> findAll();
    public void update(Long id, Item item);
    public void delete(Long id);
}
