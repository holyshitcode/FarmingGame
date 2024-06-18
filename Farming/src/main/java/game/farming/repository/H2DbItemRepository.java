package game.farming.repository;

import game.farming.domain.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Slf4j
public class H2DbItemRepository implements ItemRepository {

    private final EntityManager em;


    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        em.find(Item.class, id);
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
       return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    @Override
    public void update(Long id, Item item) {
        em.find(Item.class, id);
        item.setItemName(item.getItemName());
        item.setPrice(item.getPrice());
        item.setDescription(item.getDescription());
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(Item.class, id));
    }
}
