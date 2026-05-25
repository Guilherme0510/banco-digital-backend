package guilherme.banco_digital.service;

import guilherme.banco_digital.dto.ExtratoDTO;
import guilherme.banco_digital.dto.TransferenciaDTO;
import guilherme.banco_digital.exception.ContaNaoEncontradaException;
import guilherme.banco_digital.exception.SaldoInsuficienteException;
import guilherme.banco_digital.model.Conta;
import guilherme.banco_digital.model.TipoTransacao;
import guilherme.banco_digital.model.Transacao;
import guilherme.banco_digital.repository.ContaRepository;
import guilherme.banco_digital.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Transactional
    public void Transferir(TransferenciaDTO dto) {
        Conta contaOrigem = contaRepository.findByNmrConta(dto.getContaOrigem()).orElseThrow(() -> new ContaNaoEncontradaException("Nenhuma conta origem encontrada"));
        Conta contaDestinatario = contaRepository.findByNmrConta(dto.getContaDestinatario()).orElseThrow(() -> new ContaNaoEncontradaException("Nenhuma conta origem encontrada"));

        if (contaOrigem.getSaldo().compareTo(dto.getValorTransacao()) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente da conta");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(dto.getValorTransacao()));

        contaDestinatario.setSaldo(contaDestinatario.getSaldo().add(dto.getValorTransacao()));

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestinatario);


        Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaDestinatario);
        transacao.setValorTransacao(dto.getValorTransacao());
        transacao.setTipoTransacao(TipoTransacao.TRANSFERENCIA);
        transacaoRepository.save(transacao);
    }

    public List<ExtratoDTO> buscarExtrato(String nmrConta) {
        Conta conta = contaRepository.findByNmrConta(nmrConta).orElseThrow(() -> new ContaNaoEncontradaException("Nenhuma conta origem encontrada"));
        List<Transacao> transacoes = transacaoRepository.findTransacaoByContaOrigemOrContaDestinatario(conta, conta);

        return transacoes.stream().map(transacao -> {
                    ExtratoDTO extratoDTO = new ExtratoDTO();
                    extratoDTO.setValorExtrato(transacao.getValorTransacao());
                    extratoDTO.setTipoTransacao(transacao.getTipoTransacao());
                    extratoDTO.setRealizadoEm(transacao.getRealizadoEm());
                    extratoDTO.setContaOrigem(transacao.getContaOrigem().getNmrConta());
                    if (transacao.getContaDestinatario() != null) {
                        extratoDTO.setContaDestinatario(transacao.getContaDestinatario().getNmrConta());
                    }

                    return extratoDTO;
                })
                .collect(Collectors.toList());
    }
}
