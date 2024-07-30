package game.farming.repository;


import game.farming.domain.Player;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class H2DbPlayerRepository implements PlayerRepository {


    private final EntityManager em;


    @Override
    public Player getPlayer(long memberId) {
        return em.find(Player.class, memberId);
    }

    @Override
    public List<Player> getPlayers() {
        return em.createQuery("select p from Player p", Player.class).getResultList();
    }

    @Override
    public Player save(Player player) {
        em.persist(player);
        return player;
    }

    @Override
    public void delete(Player player) {
        em.remove(player);
    }

    @Override
    public Player update(long playerId,Player player) {
        Player foundPlayer = em.find(Player.class, playerId);
        foundPlayer.setMoney(player.getMoney());
        foundPlayer.setWorkers(player.getWorkers());
        return foundPlayer;
    }
}
