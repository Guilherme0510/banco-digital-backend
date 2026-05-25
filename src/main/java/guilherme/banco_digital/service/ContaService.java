package guilherme.banco_digital.service;

import guilherme.banco_digital.dto.CriarContaDTO;
import guilherme.banco_digital.exception.ClienteNaoEncontradoException;
import guilherme.banco_digital.exception.ContaJaExisteException;
import guilherme.banco_digital.exception.ContaNaoEncontradaException;
import guilherme.banco_digital.exception.SaldoInsuficienteException;
import guilherme.banco_digital.model.Cliente;
import guilherme.banco_digital.model.Conta;
import guilherme.banco_digital.model.TipoTransacao;
import guilherme.banco_digital.model.Transacao;
import guilherme.banco_digital.repository.ClienteRepository;
import guilherme.banco_digital.repository.ContaRepository;
import guilherme.banco_digital.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Conta criarConta(CriarContaDTO dto) {
        if(contaRepository.existsByNmrConta(dto.getNmrConta())) {
            throw new ContaJaExisteException("Numero de conta ja existe");
        }

        Cliente cliente = clienteRepository.findById(dto.getClienteId()).orElseThrow(()-> new ClienteNaoEncontradoException("Cliente nao encontrado"));

        Conta conta = new Conta();
        conta.setNmrConta(dto.getNmrConta());
        conta.setNmrAgencia(dto.getNmrAgencia());
        conta.setTipoConta(dto.getTipoConta());
        conta.setCliente(cliente);
        conta.setSaldo(BigDecimal.ZERO);

        return contaRepository.save(conta);
    }

    public void depositarConta(String nmrConta, BigDecimal valor) {
       Conta conta = contaRepository.findByNmrConta(nmrConta).orElseThrow(()-> new ContaNaoEncontradaException("Conta nao encontrado"));
       conta.setSaldo(conta.getSaldo().add(valor));
       contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setValorTransacao(valor);
        transacao.setContaOrigem(conta);
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacaoRepository.save(transacao);
    }

    public void sacarConta(String nmrConta, BigDecimal valor) {
        Conta conta = contaRepository.findByNmrConta(nmrConta).orElseThrow(()-> new ContaNaoEncontradaException("Conta nao encontrado"));
        if(conta.getSaldo().compareTo(valor) <= 0){
            throw new SaldoInsuficienteException("Saldo insuficiente, você possue" + conta.getSaldo());
        }
        conta.setSaldo(conta.getSaldo().subtract(valor));
        contaRepository.save(conta);
        Transacao transacao = new Transacao();
        transacao.setValorTransacao(valor);
        transacao.setContaOrigem(conta);
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacaoRepository.save(transacao);
    }

    public List<Conta> listarContas(Long clienteId) {
        return contaRepository.findAccountByClienteId(clienteId);
    }
}
