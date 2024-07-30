package game.farming.service;

import game.farming.domain.Player;
import game.farming.repository.H2DbPlayerRepository;
import game.farming.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlayerService {
    private final PlayerRepository repository;


    public void save(Player player) {
        repository.save(player);
    }

    public Player findById(Long id) {
        return repository.getPlayer(id);
    }

    public List<Player> findAll() {
        return repository.getPlayers();
    }
}
