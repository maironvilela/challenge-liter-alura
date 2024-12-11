package br.com.alura.liter_alura.exeption;

public class LivroJaExisteException extends RuntimeException{
    private final int errorCode;

    public LivroJaExisteException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
