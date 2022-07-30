package com.PI.back.Exceptions;

public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);
    }
}