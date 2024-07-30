package game.farming.domain;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "player")
    private Member member;

    private String playerName;

    private long money;

    private int workers;

    public Player() {
    }
    public Player(String playerName, long money, int workers) {
        this.playerName = playerName;
        this.money = money;
        this.workers = workers;
    }
}
