package guilherme.banco_digital.exception;

public class ClienteJaExisteException extends RuntimeException {
    public ClienteJaExisteException(String mensagem) {
        super(mensagem);
    }
}
