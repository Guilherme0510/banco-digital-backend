package guilherme.banco_digital.controller;

import guilherme.banco_digital.dto.CriarContaDTO;
import guilherme.banco_digital.dto.DepositoDTO;
import guilherme.banco_digital.dto.SaqueDTO;
import guilherme.banco_digital.model.Conta;
import guilherme.banco_digital.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;


    @PostMapping("/criar")
    public ResponseEntity<Conta> criarConta(@RequestBody @Valid CriarContaDTO dto){
        Conta conta = contaService.criarConta(dto);
        return ResponseEntity.status(201).body(conta);
    }

    @PostMapping("/depositar")
    public ResponseEntity<Void> depositarConta(@RequestBody @Valid DepositoDTO dto){
        contaService.depositarConta(dto.getNmrConta(), dto.getValorDeposito());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sacar")
    public ResponseEntity<Void> sacarConta(@RequestBody @Valid SaqueDTO dto){
        contaService.sacarConta( dto.getNmrConta(), dto.getValorSaque());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Conta>> buscarContaPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(contaService.listarContas(id));
    }
}
