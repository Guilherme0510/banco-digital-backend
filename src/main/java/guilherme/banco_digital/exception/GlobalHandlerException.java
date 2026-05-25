package guilherme.banco_digital.exception;

import guilherme.banco_digital.dto.ErroDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ErroDTO> handleClienteNaoEncontrado(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(404).body(new ErroDTO(404, ex.getMessage()));
    }

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ErroDTO> handleContaNaoEncontrada(ContaNaoEncontradaException ex) {
        return ResponseEntity.status(404).body(new ErroDTO(404, ex.getMessage()));
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ErroDTO> handleSaldoInsuficiente(SaldoInsuficienteException ex) {
        return ResponseEntity.status(400).body(new ErroDTO(400, ex.getMessage()));
    }

    @ExceptionHandler(ClienteJaExisteException.class)
    public ResponseEntity<ErroDTO> handleClienteJaExiste(ClienteJaExisteException ex) {
        return ResponseEntity.status(409).body(new ErroDTO(409, ex.getMessage()));
    }

    @ExceptionHandler(ContaJaExisteException.class)
    public ResponseEntity<ErroDTO> handleContaJaExiste(ContaJaExisteException ex) {
        return ResponseEntity.status(409).body(new ErroDTO(409, ex.getMessage()));
    }
}