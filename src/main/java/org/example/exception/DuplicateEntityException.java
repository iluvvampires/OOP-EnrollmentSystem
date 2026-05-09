package org.example.exception;
public class DuplicateEntityException extends Exception {


    public DuplicateEntityException(String message) {
        super(message);
    }

    // Constructor 2: Accepts message and cause (for chaining exceptions)
    public DuplicateEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor 3: Accepts only cause
    public DuplicateEntityException(Throwable cause) {
        super(cause);
    }
}