package com.playground.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * GREETING SERVICE IMPLEMENTATION
 * 
 * This class implements the GreetingService interface.
 * 
 * KEY ANNOTATION: @Service
 * ✓ Marks this class as a Spring-managed bean
 * ✓ Spring automatically creates ONE instance (Singleton scope)
 * ✓ This instance is stored in Spring's Application Context (Bean Container)
 * ✓ When any class needs GreetingService, Spring injects this implementation
 * 
 * HOW SPRING FINDS THIS IMPLEMENTATION:
 * 1. At startup, Spring scans classpath for classes with @Service annotation
 * 2. Finds GreetingServiceImpl
 * 3. Creates an instance of GreetingServiceImpl
 * 4. Stores it in the Application Context with key: "greetingServiceImpl" (lowercase first letter)
 * 5. When DemoController needs GreetingService, Spring injects this bean
 * 
 * KEY ANNOTATION: @Slf4j
 * ✓ Lombok annotation that automatically adds a logger field
 * ✓ Provides log.info(), log.debug(), log.error() methods
 * ✓ Eliminates the need to manually write: static Logger log = LoggerFactory.getLogger(...)
 */
@Service
@Slf4j
public class GreetingServiceImpl implements GreetingService {

    /**
     * CONSTRUCTOR - Called by Spring when creating this bean
     * 
     * EXECUTION:
     * 1. Spring detects @Service annotation on this class
     * 2. Spring creates a NEW instance of GreetingServiceImpl
     * 3. This constructor is called during that instantiation
     * 4. The instance is stored in the Application Context
     * 
     * LOGGING:
     * The log statements below demonstrate that Spring has successfully created this bean
     */
    public GreetingServiceImpl() {
        log.info("=====================================================================");
        log.info("[GreetingServiceImpl] Constructor is being called by Spring");
        log.info("[GreetingServiceImpl] [OK] Bean is being created by Spring");
        log.info("[GreetingServiceImpl] [INFO] To get this output, the code has:");
        log.info("[GreetingServiceImpl]       @Service annotation - marks this as a Spring-managed bean");
        log.info("[GreetingServiceImpl]       implements GreetingService - provides a contract");
        log.info("[GreetingServiceImpl] [OK] Spring STORES this bean in the Application Context (Bean Container)");
        log.info("[GreetingServiceImpl] [OK] Any class that needs GreetingService will get this implementation injected");
        log.info("=====================================================================");
    }

    /**
     * GREET METHOD - Creates and returns a personalized greeting message
     * 
     * @param name The person's name to greet
     * @return A greeting message string
     * 
     * BUSINESS LOGIC:
     * 1. Receives a name parameter from the caller
     * 2. Concatenates it with a greeting prefix
     * 3. Returns the complete greeting message
     * 
     * CALLED BY: DemoController.dependencyInjectionDemo() method
     * This demonstrates successful dependency injection - 
     * the controller uses this service without manually creating it
     */
    @Override
    public String greet(String name) {
        // OPERATION: Log that we're entering this service method
        // WHY: Helps trace the execution flow through the application
        log.info("");
        log.info(">>> INSIDE GreetingServiceImpl.greet() method");
        
        // OPERATION: Log the input parameter received
        // WHY: Helps debug by showing what data was passed to this method
        log.info(">>> Received parameter: name = '{}'", name);
        
        // OPERATION: Log that we're starting the greeting creation process
        // WHY: Shows that business logic execution has begun
        log.info(">>> Creating greeting message...");
        
        // OPERATION: Concatenate the greeting prefix with the received name
        // WHY: Creates the personalized greeting message needed by the caller
        String greeting = "Hello, " + name + "!";
        
        // OPERATION: Log the created greeting message
        // WHY: Allows verification that the correct greeting was created
        log.info(">>> Greeting message created: '{}'", greeting);
        
        // OPERATION: Log that we're returning the result
        // WHY: Traces the response path back to the caller
        log.info(">>> RETURNING greeting back to the caller (DemoController)");
        log.info("");
        
        // OPERATION: Return the greeting message to the caller
        // WHY: Sends the created greeting back to DemoController
        return greeting;
    }
}
