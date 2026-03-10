package com.playground.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

/**
 * BEAN FACTORY - Configuration class for programmatic bean creation
 * 
 * This class demonstrates creating beans programmatically using @Bean methods.
 * 
 * TWO WAYS TO CREATE BEANS:
 * 
 * METHOD 1: Annotations (we've seen this)
 * ✓ @Component, @Service, @Repository, @Controller
 * ✓ Spring auto-detects and creates beans
 * ✓ Simple, declarative approach
 * ✓ Good for beans in your own code
 * 
 * METHOD 2: @Bean Methods (this class demonstrates)
 * ✓ Explicit bean creation in @Configuration class
 * ✓ Gives full control over bean creation
 * ✓ Can call constructor with parameters
 * ✓ Can call setup methods after creation
 * ✓ Good for third-party library beans
 * ✓ Good for beans with complex initialization
 * 
 * EXAMPLE USES FOR @Bean:
 * ✓ Creating beans for external libraries (RestTemplate, etc.)
 * ✓ Creating beans with constructor parameters
 * ✓ Creating beans with post-initialization setup
 * ✓ Conditional bean creation based on properties
 * ✓ Creating multiple beans of same type with different configs
 * 
 * KEY ANNOTATION: @Configuration
 * ✓ Marks this as a Spring configuration class
 * ✓ Tells Spring to scan for @Bean methods
 * ✓ @Bean methods are called to create beans
 * ✓ Results are stored in the Application Context
 * 
 * KEY ANNOTATION: @Slf4j
 * ✓ Provides logging capability
 */
@Configuration
@Slf4j
public class BeanFactory {

    /**
     * CONSTRUCTOR - Called by Spring when loading this configuration class
     * 
     * EXECUTION:
     * 1. Spring detects @Configuration annotation
     * 2. Creates instance of BeanFactory
     * 3. This constructor is called
     * 4. Logs confirmation of configuration loading
     * 5. Spring then calls all @Bean methods in this class
     */
    public BeanFactory() {
        // OPERATION: Log that configuration class is being initialized
        // WHY: Shows that bean factory configuration is being set up
        log.info("=====================================================================");
        log.info("[OK] BeanFactory CONSTRUCTOR - Configuration class is being created");
        
        // OPERATION: Document that this class contains @Bean methods
        // WHY: Explains the purpose of this configuration class
        log.info("[OK] This class contains @Bean methods");
        
        // OPERATION: Explain how Spring processes @Bean methods
        // WHY: Shows the workflow - Spring will call each @Bean method
        log.info("[OK] Spring will call these methods to create beans");
        log.info("=====================================================================");
    }

    /**
     * STRING BEAN FACTORY METHOD
     * 
     * This method demonstrates creating a simple String bean using @Bean.
     * 
     * @return A String bean containing welcome message
     * 
     * KEY ANNOTATION: @Bean
     * ✓ Marks method as a bean factory
     * ✓ Spring calls this method to create the bean
     * ✓ Return value becomes the bean
     * ✓ Method name becomes default bean ID ("welcomeMessage")
     * 
     * WHY CREATE BEANS WITH @BEAN?
     * ✓ Useful for configuring third-party libraries
     * ✓ Allows fine control over bean creation
     * ✓ Can add custom initialization logic
     * ✓ Can set properties before returning
     * 
     * EXECUTION FLOW:
     * 1. Spring starts up and loads this @Configuration
     * 2. Spring discovers the @Bean method
     * 3. Spring calls this method ONCE at startup
     * 4. The String is created and returned
     * 5. Spring stores this String in the Application Context
     * 6. When @Autowired WelcomeMessage is used, this bean is injected
     * 7. All uses of this bean get the SAME instance (singleton by default)
     * 
     * BEAN NAME:
     * ✓ Default bean name: "welcomeMessage" (method name)
     * ✓ Can override with @Bean(name="customName")
     * ✓ Used when referencing with @Qualifier or getBean()
     * 
     * SCOPE:
     * ✓ Default scope: SINGLETON (same instance reused)
     * ✓ Can change with @Scope(SCOPE_PROTOTYPE)
     */
    @Bean
    public String welcomeMessage() {
        // OPERATION: Log that this @Bean method is being executed
        // WHY: Shows that bean creation is happening
        log.info("");
        log.info(">>> @Bean method: welcomeMessage() is being called");
        
        // OPERATION: Document that we're creating a bean
        // WHY: Explains that this is explicit bean creation
        log.info(">>> Creating a String bean via @Bean method");
        
        // OPERATION: Explain when this is called
        // WHY: Shows that this is called once at startup
        log.info(">>> Scope is SINGLETON - so this is called ONLY ONCE at startup");
        log.info("");
        
        // OPERATION: Create and return the String bean
        // WHY: Actually creates the bean object to be stored in Application Context
        return "Welcome to Spring Boot Interview Playground - Sample Edition!";
    }
}
