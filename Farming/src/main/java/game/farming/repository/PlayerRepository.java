package game.farming.repository;

import game.farming.domain.Player;

import java.util.List;

public interface PlayerRepository {
    public Player getPlayer(long memberId);
    public List<Player> getPlayers();
    public Player save(Player player);
    public void delete(Player player);
    public Player update(long playerId,Player player);
}
