package com.playground.service;

/**
 * GREETING SERVICE - Service Interface
 * 
 * This is a Java interface that defines the contract for greeting services.
 * 
 * DESIGN PATTERN: Strategy Pattern + Dependency Injection
 * - Interface separates the abstraction from implementation
 * - Allows multiple implementations (GreetingServiceImpl can be extended)
 * - Enables loose coupling - controllers depend on interface, not concrete class
 * 
 * BENEFITS:
 * ✓ Easy to test - can mock this interface
 * ✓ Easy to extend - can create different greeting implementations
 * ✓ Follows SOLID principles (Dependency Inversion)
 */
public interface GreetingService {
    
    /**
     * Generates a greeting message for the given name
     * 
     * @param name The name to include in the greeting
     * @return A greeting message string
     * 
     * EXAMPLE: greet("John") → "Hello, John!"
     */
    String greet(String name);
}
