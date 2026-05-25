package guilherme.banco_digital.repository;

import guilherme.banco_digital.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Long> {
    Optional<Conta> findByNmrConta(String nmrConta);
    List<Conta> findAccountByClienteId(Long clienteId);
    boolean existsByNmrConta(String nmrConta);
}
