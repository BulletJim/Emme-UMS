package com.emme.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyTakenException(){
        super("Email already taken");
    }
}
