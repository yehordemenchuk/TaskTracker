package com.tracker.ai.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity Not Found");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
