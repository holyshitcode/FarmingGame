package game.farming.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private long id;

    @OneToOne(mappedBy = "player")
    private Member member;

    private String playerName;

    private long money;

    private int workers;

    @OneToMany
    @JoinColumn(name = "player_id")
    private List<Item> item;

    public Player() {
    }
    public Player(String playerName, long money, int workers) {
        this.playerName = playerName;
        this.money = money;
        this.workers = workers;
    }
}
