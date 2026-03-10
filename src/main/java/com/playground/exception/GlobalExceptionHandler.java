package com.playground.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * GLOBAL EXCEPTION HANDLER - Centralized exception handling
 * 
 * This class demonstrates the @ControllerAdvice pattern which:
 * ✓ Catches exceptions thrown anywhere in the application
 * ✓ Handles them in one centralized location
 * ✓ Returns consistent error responses to clients
 * ✓ Eliminates need for try-catch blocks in controllers
 * 
 * KEY ANNOTATION: @ControllerAdvice
 * ✓ Marks this class as a global exception handler
 * ✓ All methods are triggered when matching exceptions occur
 * ✓ Applies to all controllers in the application
 * 
 * KEY ANNOTATION: @ExceptionHandler
 * ✓ Marks a method as an exception handler
 * ✓ Specifies which exception type(s) it handles
 * ✓ Spring calls this method when the exception is thrown
 * 
 * BENEFITS:
 * ✓ Single point of error handling
 * ✓ Consistent error response format
 * ✓ Cleaner controller code (no try-catch)
 * ✓ Easy to add logging for all errors
 * ✓ Easy to modify error response format globally
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * CUSTOM EXCEPTION HANDLER - Handles CustomException
     * 
     * This method is called automatically when CustomException is thrown
     * anywhere in the application.
     * 
     * @param ex The CustomException that was thrown
     * @return ResponseEntity with ErrorResponse in JSON format and HTTP status
     * 
     * EXECUTION FLOW:
     * 1. Some method throws CustomException("Something went wrong")
     * 2. Spring catches this exception
     * 3. Spring looks for a matching @ExceptionHandler method
     * 4. Spring calls this handleCustomException method
     * 5. We create an ErrorResponse object
     * 6. We return it as JSON with HTTP 400 (Bad Request)
     * 7. Client receives the error response
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        // OPERATION: Log the exception
        // WHY: Creates audit trail of errors
        log.error("[CustomException Caught] Exception message: {}", ex.getMessage());
        
        // OPERATION: Create error response
        // WHY: Provides structured error to client
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("ERR_001")
                .errorMessage("Custom Exception")
                .details(ex.getMessage())
                .build();
        
        // OPERATION: Return error response with HTTP 400 status
        // WHY: Indicates bad request / client error (not server error)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    /**
     * GENERIC EXCEPTION HANDLER - Handles all unhandled exceptions
     * 
     * This is a catch-all handler for any exception not explicitly handled.
     * 
     * @param ex The Exception that was thrown
     * @return ResponseEntity with ErrorResponse in JSON format and HTTP status
     * 
     * EXECUTION FLOW:
     * 1. An unexpected exception is thrown
     * 2. No specific @ExceptionHandler matches the exception type
     * 3. Spring catches the exception and looks for a generic handler
     * 4. Spring calls this handleException method
     * 5. We create an ErrorResponse object
     * 6. We return it as JSON with HTTP 500 (Internal Server Error)
     * 7. Client receives a generic error response
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // OPERATION: Log the unexpected exception
        // WHY: Critical for debugging unexpected errors
        log.error("[Exception Caught] Unexpected error: {}", ex.getMessage());
        log.error("[Exception Stack Trace]", ex);
        
        // OPERATION: Create error response
        // WHY: Provides safe error message to client without revealing internals
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("ERR_500")
                .errorMessage("Internal Server Error")
                .details("An unexpected error occurred. Please contact support.")
                .build();
        
        // OPERATION: Return error response with HTTP 500 status
        // WHY: Indicates server error (not client's fault)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
