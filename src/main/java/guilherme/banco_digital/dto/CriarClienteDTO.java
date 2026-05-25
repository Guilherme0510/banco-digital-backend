package guilherme.banco_digital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarClienteDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O email é obrigatório!")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank
    @Size(min = 6, message = "A senha deve conter pelo menos 6 caracteres")
    private String senha;

    @NotBlank(message = "CPF é obrigatório!")
    @Size(min = 11, max=11, message = "CPF deve conter 11 digítos")
    @Pattern(regexp = "\\d{11}", message = "CPF é apenas números")
    private String cpf;

    @NotBlank(message = "telefone é obrigatorio")
    @Size(min = 10, max = 11, message = "Telefone deve conter DDD + número")
    @Pattern(regexp = "\\d{11}", message = "telefone é apenas números")
    private String telefone;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;
}
