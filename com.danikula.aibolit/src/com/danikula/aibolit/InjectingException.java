package com.danikula.aibolit;

/**
 * Notify about any injection problem
 * 
 * @author Alexey Danilov
 * 
 */
public class InjectingException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    /**
     * {@inheritDoc}
     */
    public InjectingException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public InjectingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public InjectingException(Throwable cause) {
        super(cause);
    }
}
