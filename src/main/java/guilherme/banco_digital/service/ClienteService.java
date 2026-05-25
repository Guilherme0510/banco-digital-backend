package guilherme.banco_digital.service;

import guilherme.banco_digital.dto.CriarClienteDTO;
import guilherme.banco_digital.exception.ClienteJaExisteException;
import guilherme.banco_digital.exception.ClienteNaoEncontradoException;
import guilherme.banco_digital.model.Cliente;
import guilherme.banco_digital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Cliente criarCliente(CriarClienteDTO dto) {
        if (clienteRepository.existsByCpf(dto.getCpf())) {
            throw new ClienteJaExisteException("CPF ja existe");
        }
        if(clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ClienteJaExisteException("email ja existe");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEstado(dto.getEstado());
        cliente.setSenha(passwordEncoder.encode(dto.getSenha()));

        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(()-> new ClienteNaoEncontradoException("Id do cliente nao encontrado"));
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(()-> new ClienteNaoEncontradoException("CPF do cliente não encontrado"));
    }

    public Cliente buscarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email).orElseThrow(()-> new ClienteNaoEncontradoException("Email do cliente não encontrado"));
    }

    public List<Cliente> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public void deletarClientePorCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(() -> new ClienteNaoEncontradoException("Cpf nao encontrado"));
        clienteRepository.delete(cliente);
    }
}
