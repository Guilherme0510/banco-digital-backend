package guilherme.banco_digital.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Insira um email válido")
    private String email;

    @NotBlank(message = "Senha é obrigatório")
    private String senha;
}
