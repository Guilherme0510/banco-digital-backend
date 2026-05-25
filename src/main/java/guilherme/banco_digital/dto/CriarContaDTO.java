package guilherme.banco_digital.dto;

import guilherme.banco_digital.model.TipoConta;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarContaDTO {

    @NotBlank(message = "Número da conta é obrigatório")
    @Size(min = 6, max = 6, message = "Número da conta deve ter 6 dígitos")
    private String nmrConta;

    @NotBlank(message = "Número da agência é obrigatório")
    @Size(min = 3, max = 3, message = "Número da agência deve ter 3 dígitos")
    private String nmrAgencia;

    @NotNull(message = "Tipo da conta é obrigatório")
    private TipoConta tipoConta;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;
}