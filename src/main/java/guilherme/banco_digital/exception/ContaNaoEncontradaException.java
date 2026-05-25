package guilherme.banco_digital.exception;

public class ContaNaoEncontradaException extends RuntimeException {
    public  ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
