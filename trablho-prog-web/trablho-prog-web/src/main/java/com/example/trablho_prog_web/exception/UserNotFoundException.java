package com.example.trablho_prog_web.exception;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String id){
        super("Usuário nao encontrado! (ID: " + id + ")");
    }
}
