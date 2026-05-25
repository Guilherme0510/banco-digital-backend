package guilherme.banco_digital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "contas_poupancas")
public class ContaPoupanca extends Conta {
    private BigDecimal taxaRendimento;
}
