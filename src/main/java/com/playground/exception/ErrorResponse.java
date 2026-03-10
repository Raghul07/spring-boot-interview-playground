package com.playground.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ERROR RESPONSE DTO - Standardized error response format
 * 
 * When an exception occurs, this DTO wraps the error information
 * and sends it to the client in a consistent JSON format.
 * 
 * JSON EXAMPLE:
 * {
 *     "errorCode": "ERR_001",
 *     "errorMessage": "Invalid input",
 *     "details": "Please provide valid parameters"
 * }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    /**
     * Unique error code identifier
     * EXAMPLES:
     * ✓ "ERR_001" - Validation error
     * ✓ "ERR_002" - Resource not found
     * ✓ "ERR_500" - Internal server error
     */
    private String errorCode;
    
    /**
     * Brief error message for the client
     * EXAMPLES:
     * ✓ "Invalid input"
     * ✓ "Bean not found"
     * ✓ "Internal server error"
     */
    private String errorMessage;
    
    /**
     * Detailed explanation of the error
     * EXAMPLES:
     * ✓ "The 'name' parameter cannot be null"
     * ✓ "Request failed: resources unavailable"
     */
    private String details;
}
