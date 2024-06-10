package game.farming.repository;

import game.farming.domain.Item;

import java.util.List;

public interface ItemRepository {

    public Item save(Item item);
    public Item findById(Long id);
    public List<Item> findAll();
}
