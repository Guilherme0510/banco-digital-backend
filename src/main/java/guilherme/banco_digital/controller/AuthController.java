package guilherme.banco_digital.controller;


import guilherme.banco_digital.dto.LoginDTO;
import guilherme.banco_digital.dto.TokenDTO;
import guilherme.banco_digital.dto.UsuarioDTO;
import guilherme.banco_digital.model.Cliente;
import guilherme.banco_digital.repository.ClienteRepository;
import guilherme.banco_digital.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private JwtService jwtService;



    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO dto){

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getSenha()
                )
        );

        Cliente cliente = clienteRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = jwtService.gerarToken(auth.getName());

        TokenDTO response = new TokenDTO(
                token,
                new UsuarioDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEmail()
                )
        );

        return ResponseEntity.ok(response);
    }
}
