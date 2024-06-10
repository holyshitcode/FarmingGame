package game.farming.domain;


import lombok.Data;
import lombok.Getter;

@Data
public class Item {
    private Long id;
    private String name;
    private String description;
    private int price;

    public Item() {

    }
    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


}
