package game.farming.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
    @NotEmpty
    @NotBlank
    private String LoginId;
    @NotEmpty
    @NotBlank
    private String Password;
}
