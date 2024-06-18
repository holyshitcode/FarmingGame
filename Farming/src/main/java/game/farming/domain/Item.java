package game.farming.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String itemName;

    @NotEmpty
    private String description;


    @Min(0)
    @Max(1000000000)
    private Integer price;

    @OneToOne
    @JoinColumn(name = "attach_file_id")
    private UploadFile attachFile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_files_id")
    private UploadFile imageFiles;



    public Item() {

    }
    public Item(String name, String description, int price) {
        this.itemName = name;
        this.description = description;
        this.price = price;
    }



}
