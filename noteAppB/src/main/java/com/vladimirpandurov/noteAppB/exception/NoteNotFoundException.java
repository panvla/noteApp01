package com.vladimirpandurov.noteAppB.exception;

public class NoteNotFoundException extends Exception{
    public NoteNotFoundException(String message) {
        super(message);
    }
}
