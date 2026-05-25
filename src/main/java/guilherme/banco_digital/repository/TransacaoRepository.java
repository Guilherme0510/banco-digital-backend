package guilherme.banco_digital.repository;

import guilherme.banco_digital.model.Conta;
import guilherme.banco_digital.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findTransacaoByContaOrigem(Conta contaOrigem);
    List<Transacao> findTransacaoByContaDestinatario(Conta contaDestinatario);
    List<Transacao> findTransacaoByContaOrigemOrContaDestinatario(Conta nmrConta, Conta contaDestinatario);
    List<Transacao> findTransacaoByTipoTransacao(Enum tipoTransacao);
}
