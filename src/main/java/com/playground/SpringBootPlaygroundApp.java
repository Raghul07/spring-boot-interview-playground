package com.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MAIN APPLICATION CLASS - Spring Boot Entry Point
 * 
 * This class serves as the entry point for the entire Spring Boot application.
 * 
 * KEY ANNOTATION: @SpringBootApplication
 * This single annotation combines three powerful annotations:
 * ✓ @Configuration - marks this class as a Spring configuration class
 * ✓ @EnableAutoConfiguration - enables Spring Boot's auto-configuration
 * ✓ @ComponentScan - scans for @Component, @Service, @Repository, @Controller classes
 * 
 * FUNCTIONALITY:
 * - Initializes the Spring Application Context
 * - Creates all beans automatically
 * - Starts the embedded Tomcat server (default port: 8080)
 * - Enables dependency injection throughout the application
 */
@SpringBootApplication
public class SpringBootPlaygroundApp {

    /**
     * MAIN METHOD - Application execution starts here
     * 
     * @param args Command-line arguments passed to the application
     * 
     * EXECUTION FLOW:
     * 1. JVM calls this main() method when the application starts
     * 2. SpringApplication.run() initializes the Spring Boot application
     * 3. Spring scans the entire project for:
     *    - Beans (@Service, @Component, @Repository, @Controller)
     *    - Configuration classes (@Configuration)
     *    - Controllers with endpoints (@RestController)
     * 4. All dependencies are injected automatically
     * 5. The embedded Tomcat server starts and listens for HTTP requests
     * 6. Application is ready to handle API requests
     */
    public static void main(String[] args) {
        // OPERATION: Start the Spring Boot application
        // WHY: This initializes the entire Spring Framework and all configured beans
        SpringApplication.run(SpringBootPlaygroundApp.class, args);
    }

}
