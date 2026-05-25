package guilherme.banco_digital.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {

    @NotNull(message = "Digite um valor")
    @DecimalMin(value= "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valorTransacao;

    @NotBlank(message = "Número da conta origem é obrigatório")
    private String contaOrigem;

    @NotBlank(message = "Número da conta destinatario é obrigatório")
    private String contaDestinatario;
}
