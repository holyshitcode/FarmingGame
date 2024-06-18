package game.farming.controller.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {


    private long ItemId;
    @NotNull
    @NotEmpty
    private String itemName;


    private MultipartFile imageFiles;

    private MultipartFile attachFile;

    @NotEmpty
    private String description;

    @NotNull
    @Min(0)
    @Max(1000000000)
    private int price;
}
