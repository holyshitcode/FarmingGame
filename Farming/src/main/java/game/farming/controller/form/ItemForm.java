package game.farming.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {


    private long ItemId;
    @NotNull
    private String itemName;


    private List<MultipartFile> imageFiles;

    private MultipartFile attachFile;


    private String description;


    @NotNull
    private int price;
}
