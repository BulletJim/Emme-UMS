package com.emme.exceptions;

public class RoleNotFoundInException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RoleNotFoundInException(){
        super("Role not found on DB");
    }

}
