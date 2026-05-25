package guilherme.banco_digital.dto;

import guilherme.banco_digital.model.Conta;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
public class DepositoDTO {
    
    @NotNull(message = "Digite um valor")
    @DecimalMin(value= "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valorDeposito;

    @NotBlank(message = "Número da conta é obrigatório")
    private String nmrConta;


}
