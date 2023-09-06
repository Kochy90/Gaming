package com.gaming_platform.exceptions;

public class InvalidPlayerException extends Exception{

    public InvalidPlayerException(String errorMessage) {
        super(errorMessage);
    }
}
