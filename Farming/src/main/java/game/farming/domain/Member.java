package game.farming.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Member(String name, String loginId, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public Member() {
    }
}
