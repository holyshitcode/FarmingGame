package game.farming.domain;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class Item {
    private Long id;
    private String itemName;
    private String description;
    private int price;

    private UploadFile attachFile;
    private List<UploadFile> imageFiles;



    public Item() {

    }
    public Item(String name, String description, int price) {
        this.itemName = name;
        this.description = description;
        this.price = price;
    }


}
