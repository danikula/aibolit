package com.danikula.aibolit;

/**
 * Contains some usefull assert methods for validating values of variables and parameters.
 * 
 * @author Alexey Danilov
 * 
 */
public class Validate {

    /**
     * Checks variable on <code>null</code>
     * 
     * @param object Object variable to be checked
     * @param message String message of exception to be throwed if parameter is <code>null</code>
     * @throws IllegalArgumentException if <code>object</code> is <code>null</code>
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks contition on <code>true</code>
     * 
     * @param condition boolean condition to check
     * @param message String message of exception to be throwed if condition is not <code>true</code>
     * @throws IllegalArgumentException if <code>condition</code> is not <code>true</code>
     */
    public static void checkTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalArgumentException(message);
        }
    }

}
