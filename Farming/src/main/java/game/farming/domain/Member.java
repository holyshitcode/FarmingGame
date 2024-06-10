package game.farming.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

@Data
public class Member {
    private Long id;
    @NotNull
    @NotBlank
    private String username;
    @Range(min = 0 , max = 99999)
    private Long money;

    public Long getId() {
        return id;
    }

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

}
