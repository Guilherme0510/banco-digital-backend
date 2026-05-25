package guilherme.banco_digital.controller;

import guilherme.banco_digital.dto.ExtratoDTO;
import guilherme.banco_digital.dto.TransferenciaDTO;
import guilherme.banco_digital.model.Transacao;
import guilherme.banco_digital.service.TransacaoService;
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
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transferir")
    public ResponseEntity<Void> transferir(@RequestBody @Valid TransferenciaDTO dto) {
        transacaoService.Transferir(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/extrato/{nmrConta}")
    public ResponseEntity<List<ExtratoDTO>> buscarExtrato(@PathVariable String nmrConta) {
        return ResponseEntity.ok(transacaoService.buscarExtrato(nmrConta));
    }
}
