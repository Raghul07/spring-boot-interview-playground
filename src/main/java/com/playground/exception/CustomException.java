package com.playground.exception;

/**
 * CUSTOM EXCEPTION - Custom exception for application errors
 * 
 * This exception is thrown when a custom application error occurs.
 * 
 * KEY POINT:
 * ✓ Extends RuntimeException - unchecked exception
 * ✓ No need to declare "throws" in method signatures
 * ✓ Caught by GlobalExceptionHandler for centralized error handling
 * 
 * WHY CUSTOM EXCEPTIONS:
 * ✓ More descriptive than generic Exception
 * ✓ Allows specific exception handling for business logic errors
 * ✓ Separates application errors from system errors
 */
public class CustomException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with error message
     * 
     * @param message The error message to include
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * Constructor with error message and cause
     * 
     * @param message The error message to include
     * @param cause The root cause of the exception
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
