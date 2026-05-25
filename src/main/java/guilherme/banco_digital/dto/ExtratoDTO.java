package guilherme.banco_digital.dto;

import guilherme.banco_digital.model.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoDTO {

    private BigDecimal valorExtrato;

    private LocalDateTime realizadoEm;

    private TipoTransacao tipoTransacao;

    private String contaOrigem;

    private String contaDestinatario;

}
