package fr.uga.m1miage.pc.domain.exceptions;

public class JeuCreationException extends RuntimeException{
    public JeuCreationException(String message,Throwable cause) {
        super(message,cause);
    }
}
