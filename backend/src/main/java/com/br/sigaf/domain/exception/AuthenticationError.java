package com.br.sigaf.domain.exception;

public class AuthenticationError extends RuntimeException {

    public AuthenticationError(String mensagem) {
        super(mensagem);
    }
}
