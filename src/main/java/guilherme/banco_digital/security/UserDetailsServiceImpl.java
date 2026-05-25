package guilherme.banco_digital.security;

import guilherme.banco_digital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return clienteRepository.findByEmail(email)
                .map(cliente -> User.builder()
                        .username(cliente.getEmail())
                        .password(cliente.getSenha())
                        .roles("USER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
    }
}
