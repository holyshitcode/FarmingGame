package game.farming.domain;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String username;
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
