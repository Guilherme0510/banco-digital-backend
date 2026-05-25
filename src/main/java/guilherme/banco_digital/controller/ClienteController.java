package guilherme.banco_digital.controller;


import guilherme.banco_digital.dto.CriarClienteDTO;
import guilherme.banco_digital.model.Cliente;
import guilherme.banco_digital.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody @Valid CriarClienteDTO dto) {
        Cliente cliente = clienteService.criarCliente(dto);
        return ResponseEntity.status(201).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodosClientes() {
        List<Cliente> cliente = clienteService.buscarTodosClientes();
        return ResponseEntity.status(200).body(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.status(200).body(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.status(200).body(cliente);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> buscarClientePorEmail(@PathVariable String email) {
        Cliente cliente = clienteService.buscarClientePorEmail(email);
        return ResponseEntity.status(200).body(cliente);
    }

    @DeleteMapping("/cpf/{cpf}")
    public void deletarClientePorCpf(@PathVariable String cpf) {
       clienteService.deletarClientePorCpf(cpf);
       ResponseEntity.status(204);
    }

}
