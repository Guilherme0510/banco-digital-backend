package guilherme.banco_digital.exception;

public class ContaJaExisteException extends RuntimeException {
    public ContaJaExisteException(String mensagem) {
        super(mensagem);
    }
}
