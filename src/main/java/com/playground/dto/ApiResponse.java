package com.playground.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API RESPONSE DTO (Data Transfer Object)
 * 
 * This class represents a standard API response sent to clients.
 * 
 * PURPOSE:
 * ✓ Wraps response data in a consistent format
 * ✓ Provides metadata about the response (concept, explanation, etc.)
 * ✓ Delivers both success data and educational information
 * ✓ Serializes to JSON automatically by Spring
 * 
 * JSON EXAMPLE:
 * {
 *     "concept": "Dependency Injection",
 *     "message": "Service successfully invoked",
 *     "processFlow": "Step 1 → Step 2 → Step 3",
 *     "explanation": "This demonstrates constructor injection...",
 *     "data": { ... actual business data ... }
 * }
 * 
 * KEY ANNOTATIONS: @Data
 * ✓ Lombok annotation generating:
 * ✓ Getters and setters for all fields
 * ✓ toString(), equals(), hashCode()
 * ✓ Eliminates boilerplate code
 * 
 * KEY ANNOTATIONS: @Builder
 * ✓ Generates Builder pattern for object creation
 * ✓ Allows fluent API: ApiResponse.builder().concept("...").build()
 * ✓ Optional parameters make construction clean
 * 
 * KEY ANNOTATIONS: @NoArgsConstructor, @AllArgsConstructor
 * ✓ @NoArgsConstructor: Creates no-argument constructor
 * ✓ @AllArgsConstructor: Creates constructor with all fields
 * ✓ Provides flexibility in object instantiation
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    
    /**
     * Spring Boot Concept being demonstrated
     * EXAMPLES:
     * ✓ "Dependency Injection (Constructor Injection)"
     * ✓ "@Bean Annotation"
     * WHY: Helps clients understand what concept is being shown
     */
    private String concept;
    
    /**
     * Brief message about the operation
     * EXAMPLES:
     * ✓ "Successfully created bean"
     * ✓ "Dependency injected successfully"
     * WHY: Provides quick summary of operation result
     */
    private String message;
    
    /**
     * Description of how the process flows
     * EXAMPLES:
     * ✓ "Request → Controller → Service → Response"
     * ✓ "Step 1: Spring detects @Bean → Step 2: Creates instance → Step 3: Returns bean"
     * WHY: Shows clients the architecture and execution path
     */
    private String processFlow;
    
    /**
     * Detailed explanation of the concept
     * Can be a String, List of steps, or any other format
     * EXAMPLES:
     * ✓ "This demonstrates how Spring automatically injects dependencies..."
     * ✓ List of strings: ["Step 1: Spring detects...", "Step 2: ..."]
     * ✓ Map with key-value pairs
     * WHY: Educational content helping developers learn Spring concepts
     */
    private Object explanation;
    
    /**
     * The actual business response data
     * TYPE: Object (can be any data type)
     * EXAMPLES:
     * ✓ Simple String: "Hello, World!"
     * ✓ Complex Object: User, Product, Order details
     * ✓ Collection: List of data
     * WHY: Contains the actual result/payload of the API call
     */
    private Object data;
    
    /**
     * Practical Try-Out Steps for learning
     * TYPE: Array of Strings
     * EXAMPLES:
     * ✓ "Step 1: Open BeanFactory.java"
     * ✓ "Step 2: Look at the welcomeMessage() method"
     * ✓ "Step 3: Notice @Bean annotation"
     * WHY: Helps users understand by experimenting with code
     */
    private String[] tryout;}