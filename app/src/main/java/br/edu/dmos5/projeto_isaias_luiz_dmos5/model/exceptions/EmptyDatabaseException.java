package br.edu.dmos5.projeto_isaias_luiz_dmos5.model.exceptions;

public class EmptyDatabaseException extends RuntimeException {

    public EmptyDatabaseException() {
    }

    public EmptyDatabaseException(String message) {
        super(message);
    }

    public EmptyDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyDatabaseException(Throwable cause) {
        super(cause);
    }
}
