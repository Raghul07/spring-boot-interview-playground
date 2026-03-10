package com.playground.controller;

import com.playground.dto.ApiResponse;
import com.playground.service.GreetingService;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

/**
 * DEMO CONTROLLER - REST API endpoints for Spring Boot demonstrations
 * 
 * This controller demonstrates core Spring Boot concepts through interactive API endpoints.
 * 
 * KEY ANNOTATION: @RestController
 * ✓ Combines @Controller + @ResponseBody
 * ✓ Automatically serializes returned objects to JSON
 * ✓ HTTP method is specified by @GetMapping, @PostMapping, etc.
 * ✓ No need to manually write response body
 * 
 * KEY ANNOTATION: @RequestMapping("/demo")
 * ✓ Base path for all endpoints in this controller
 * ✓ All methods are prefixed with /demo
 * ✓ Full endpoints: /demo/bean, /demo/di, /demo/health, etc.
 * 
 * KEY ANNOTATION: @Slf4j
 * ✓ Provides logging via Lombok
 * ✓ All endpoint methods log their execution flow
 * ✓ Helps understand request processing and dependency usage
 * 
 * ENDPOINTS PROVIDED:
 * ✓ /demo/bean - @Bean annotation (Creates beans programmatically)
 * ✓ /demo/di - Demonstrates Dependency Injection (Constructor Injection)
 * ✓ /demo/health - Health check endpoint
 * 
 * CONSTRUCTOR INJECTION:
 * This controller demonstrates best-practice constructor injection:
 * ✓ All dependencies declared as final fields
 * ✓ All dependencies injected via constructor
 * ✓ Spring uses constructor to inject beans automatically
 * ✓ Dependencies are immutable and required (no nulls)
 * 
 * HOW TO USE:
 * 1. Start the application: mvn spring-boot:run
 * 2. Visit http://localhost:8080/swagger-ui.html (Swagger UI)
 * 3. Or use curl: curl http://localhost:8080/demo/di
 * 4. Observe server logs showing execution flow
 * 5. Notice which beans are created and when
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    // FIELD: Service for generating greetings
    // WHY: Injected as a dependency to demonstrate Dependency Injection
    private final GreetingService greetingService;
    
    // FIELD: Welcome message bean created by @Bean
    // WHY: Injected to demonstrate @Bean annotation usage
    private final String welcomeMessage;

    /**
     * CONSTRUCTOR - Demonstrates Constructor Injection
     * 
     * This is a critical concept in Spring - constructor injection.
     * 
     * KEY POINT: Constructor parameters are automatically injected by Spring!
     * 
     * EXECUTION FLOW:
     * 1. Application starts
     * 2. Spring detects @RestController annotation on this class
     * 3. Spring knows DemoController needs to be created
     * 4. Spring looks at the constructor parameters:
     *    - GreetingService greetingService
     *    - String welcomeMessage
     * 5. Spring searches in the Application Context for these beans
     * 6. Spring finds:
     *    a) GreetingServiceImpl implements GreetingService
     *    b) welcomeMessage bean created by @Bean in BeanFactory
     * 7. Spring creates instances (if not already created)
     * 8. Spring calls this constructor with the beans as arguments
     * 9. The beans are stored as final fields in this controller
     * 
     * WHY CONSTRUCTOR INJECTION IS BEST:
     * ✓ Dependencies are immutable (final)
     * ✓ Dependencies are required (no nulls possible)
     * ✓ Dependencies are visible in the constructor signature
     * ✓ Easy to test (can create instances manually)
     * ✓ Spring automatically wires dependencies
     * 
     * WHAT SPRING DID:
     * Spring automatically:
     * ✓ Found the constructor
     * ✓ Analyzed parameter types (GreetingService, String)
     * ✓ Searched for matching beans in Application Context
     * ✓ Found GreetingServiceImpl for GreetingService
     * ✓ Found welcomeMessage bean for String
     * ✓ Called this constructor with found beans
     * ✓ Stored parameters as final fields
     * 
     * RESULT:
     * This controller can now use greetingService.greet() and welcomeMessage
     * without creating them manually - Spring did it all!
     * 
     * @param greetingService The GreetingService implementation (injected by Spring)
     * @param welcomeMessage The welcome message String bean (injected by Spring)
     */
    public DemoController(GreetingService greetingService, String welcomeMessage) {
        log.info("");
        log.info("=====================================================================");
        log.info("[DemoController] Constructor is being called by Spring");
        log.info("[DemoController] [OK] Dependencies are being injected:");
        log.info("[DemoController]     ✓ GreetingService injected");
        log.info("[DemoController]     ✓ String (welcomeMessage) injected");
        log.info("[DemoController] [INFO] This is DEPENDENCY INJECTION in action!");
        log.info("[DemoController] [INFO] Spring automatically found and injected the beans");
        log.info("[DemoController] [INFO] No manual creation needed: new GreetingServiceImpl()");
        log.info("[DemoController] [INFO] Spring handles all of that automatically");
        log.info("[DemoController] [OK] Dependencies are now ready for use");
        log.info("=====================================================================");
        log.info("");
        
        // OPERATION: Store the injected dependencies as final fields
        // WHY: These beans are now available for use in all other methods
        this.greetingService = greetingService;
        this.welcomeMessage = welcomeMessage;
    }

    /**
     * DEPENDENCY INJECTION DEMO - Shows how Spring injects dependencies
     * 
     * This endpoint demonstrates constructor injection in action.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /demo/di
     * PARAMETERS: name (query parameter, default: "World")
     * 
     * EXAMPLE REQUESTS:
     * ✓ http://localhost:8080/demo/di
     * ✓ http://localhost:8080/demo/di?name=John
     * ✓ http://localhost:8080/demo/di?name=Alice
     * 
     * URL PATH PARAMETERS:
     * @RequestParam String name - Extracts 'name' from query string
     * defaultValue = "World" - Uses "World" if name is not provided
     * required = false - Makes parameter optional
     * 
     * EXAMPLE RESPONSES:
     * Request: GET /demo/di?name=John
     * Response: {
     *     "concept": "Dependency Injection (Constructor Injection)",
     *     "message": "GreetingService successfully injected by Spring!",
     *     "processFlow": "Spring creates @Service → Injects into @RestController → Method uses it",
     *     "explanation": "This is constructor injection - Spring automatically found the GreetingService bean...",
     *     "data": "Hello, John!"
     * }
     * 
     * @param name The person's name to greet (default: "World")
     * @return ApiResponse with the greeting and educational information
     * 
     * EXECUTION FLOW:
     * 1. Client makes HTTP request: GET /demo/di?name=John
     * 2. Spring routes request to this method
     * 3. Spring extracts "name" parameter from query string
     * 4. Method body executes:
     *    a) Log entry point
     *    b) Uses this.greetingService (injected by constructor)
     *    c) Calls greetingService.greet(name)
     *    d) Receives greeting result
     *    e) Creates ApiResponse with educational information
     *    f) Returns ApiResponse (auto-serialized to JSON)
     * 5. Spring serializes the response to JSON
     * 6. Response is sent to client with HTTP 200 (OK)
     * 7. Client receives JSON response
     * 
     * KEY CONCEPTS DEMONSTRATED:
     * ✓ Constructor Injection - greetingService was injected by Spring
     * ✓ Service Method Call - greetingService.greet(name)
     * ✓ Request Parameters - @RequestParam String name
     * ✓ Response DTO - ApiResponse wraps the response
     * ✓ Logging - Log statements show execution flow
     * ✓ JSON Serialization - ApiResponse auto-converts to JSON
     */
    @GetMapping("/di")
    public ApiResponse dependencyInjectionDemo(
            @RequestParam(value = "name", defaultValue = "World", required = false) String name) {
        
        // OPERATION: Log method entry
        // WHY: Shows that the API endpoint is being called
        log.info("");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("REQUEST RECEIVED: GET /demo/di");
        log.info("Received parameter: name = '{}'", name);
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        
        // OPERATION: Call the injected service
        // WHY: This demonstrates that the dependency injection is working
        // NOTE: this.greetingService was injected by Spring's constructor
        log.info(">>> Calling injected greetingService.greet('{}')...", name);
        String greeting = this.greetingService.greet(name);
        
        // OPERATION: Build and return the API response
        // WHY: Wraps the result in educational information
        log.info(">>> Building API response with explanation...");
        ApiResponse response = ApiResponse.builder()
                .concept("Dependency Injection (Constructor Injection)")
                .message("GreetingService successfully injected by Spring!")
                .processFlow("Spring creates @Service → Injects into @RestController → Method uses it")
                .explanation(Arrays.asList(
                    "1. DemoController constructor declares a GreetingService parameter",
                    "2. Spring sees this constructor parameter",
                    "3. Spring searches in Application Context for a GreetingService bean",
                    "4. Spring finds GreetingServiceImpl (marked with @Service)",
                    "5. Spring creates an instance of GreetingServiceImpl",
                    "6. Spring calls the constructor with this instance",
                    "7. Now we can use this.greetingService without creating it ourselves",
                    "8. This is called CONSTRUCTOR INJECTION - the best practice",
                    "9. No @Autowired needed - Spring detects it from constructor signature!"
                ))
                .data(greeting)
                .tryout(new String[]{
                    "Step 1: Open DemoController.java in your editor",
                    "Step 2: Look at the constructor - it has GreetingService parameter",
                    "Step 3: Notice there's NO @Autowired annotation needed",
                    "Step 4: Spring automatically injects GreetingService from the constructor parameter",
                    "Step 5: The greet() method is called from this.greetingService",
                    "Step 6: Watch the logs - you'll see 'greetingService.greet()' being called",
                    "Result: This is DEPENDENCY INJECTION in action!"
                })
                .build();
        
        // OPERATION: Log the response being returned
        // WHY: Shows that the request is being answered
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("RESPONSE READY: {}", response.getConcept());
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("");
        
        // OPERATION: Return the response
        // WHY: Spring will serialize this to JSON and send to client
        return response;
    }

    /**
     * @BEAN ANNOTATION DEMO - Shows how @Bean creates beans programmatically
     * 
     * This endpoint demonstrates the @Bean annotation in action.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /demo/bean
     * PARAMETERS: None
     * 
     * EXAMPLE REQUEST:
     * ✓ http://localhost:8080/demo/bean
     * 
     * EXAMPLE RESPONSE:
     * {
     *     "concept": "@Bean Annotation",
     *     "message": "Bean successfully created and injected!",
     *     "processFlow": "@Configuration detected → @Bean method called → Instance created → Stored in Context",
     *     "explanation": "@Bean is used to create beans programmatically...",
     *     "data": "Welcome to Spring Boot Interview Playground - Sample Edition!"
     * }
     * 
     * @return ApiResponse with bean information and educational content
     * 
     * EXECUTION FLOW:
     * 1. Client makes HTTP request: GET /demo/bean
     * 2. Spring routes request to this method
     * 3. Method body executes:
     *    a) Log entry point
     *    b) Uses this.welcomeMessage (injected by constructor)
     *    c) Creates ApiResponse with educational information
     *    d) Returns ApiResponse (auto-serialized to JSON)
     * 4. Spring serializes the response to JSON
     * 5. Response is sent to client with HTTP 200 (OK)
     * 6. Client receives JSON response
     * 
     * KEY CONCEPTS DEMONSTRATED:
     * ✓ @Bean Annotation - welcomeMessage was created by BeanFactory.welcomeMessage()
     * ✓ @Configuration - BeanFactory is a configuration class
     * ✓ Singleton Scope - welcomeMessage bean is created once and reused
     * ✓ Constructor Injection - welcomeMessage was injected via constructor
     * ✓ Response DTO - ApiResponse wraps the response
     */
    @GetMapping("/bean")
    public ApiResponse beanAnnotationDemo() {
        
        // OPERATION: Log method entry
        // WHY: Shows that the API endpoint is being called
        log.info("");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("REQUEST RECEIVED: GET /demo/bean");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        
        // OPERATION: Use the injected welcomeMessage bean
        // WHY: Shows that the @Bean worked correctly
        // NOTE: this.welcomeMessage was created by BeanFactory.welcomeMessage() @Bean method
        log.info(">>> Using injected welcomeMessage bean: '{}'", this.welcomeMessage);
        
        // OPERATION: Build and return the API response
        // WHY: Wraps the bean in educational information
        log.info(">>> Building API response with explanation...");
        ApiResponse response = ApiResponse.builder()
                .concept("@Bean Annotation")
                .message("Bean successfully created and injected by Spring!")
                .processFlow("@Configuration detected → @Bean method called → Instance created → Stored in Application Context")
                .explanation(Arrays.asList(
                    "1. BeanFactory class is marked with @Configuration",
                    "2. BeanFactory contains a method marked with @Bean",
                    "3. At startup, Spring detects @Bean annotation",
                    "4. Spring calls the welcomeMessage() method",
                    "5. A String object is created and returned",
                    "6. Spring stores this String in the Application Context",
                    "7. When DemoController constructor executes, Spring injects this bean",
                    "8. Now we can use this.welcomeMessage without creating it",
                    "9. @Bean is useful for: third-party library beans, complex configurations"
                ))
                .data(this.welcomeMessage)
                .tryout(new String[]{
                    "Step 1: Open BeanFactory.java in your editor",
                    "Step 2: Look at the welcomeMessage() method - notice @Bean annotation",
                    "Step 3: The method name 'welcomeMessage' becomes the bean ID/name",
                    "Step 4: See how method creates String object and returns it",
                    "Step 5: Open DemoController.java - this bean is injected in constructor",
                    "Step 6: Watch the logs - look for 'BeanFactory CONSTRUCTOR' and welcomeMessage bean creation",
                    "Result: You'll see how @Bean gives full control over bean creation!"
                })
                .build();
        
        // OPERATION: Log the response being returned
        // WHY: Shows that the request is being answered
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("RESPONSE READY: {}", response.getConcept());
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("");
        
        // OPERATION: Return the response
        // WHY: Spring will serialize this to JSON and send to client
        return response;
    }

    /**
     * HEALTH CHECK - Simple health check endpoint
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /demo/health
     * 
     * EXAMPLE REQUEST:
     * ✓ http://localhost:8080/demo/health
     * 
     * EXAMPLE RESPONSE:
     * {
     *     "concept": "Health Check",
     *     "message": "Application is running",
     *     "processFlow": "Health Check Endpoint",
     *     "explanation": "Simple health check endpoint to verify application is running",
     *     "data": "OK"
     * }
     * 
     * @return ApiResponse indicating application health status
     */
    @GetMapping("/health")
    public ApiResponse healthCheck() {
        
        // OPERATION: Log health check request
        // WHY: Helps monitor application requests
        log.info("");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("HEALTH CHECK: Application is running");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("");
        
        // OPERATION: Build and return health response
        // WHY: Provides status indication to client
        return ApiResponse.builder()
                .concept("Health Check")
                .message("Application is running")
                .processFlow("Health Check Endpoint")
                .explanation("Simple health check endpoint to verify application is running")
                .data("OK")
                .tryout(new String[]{
                    "This is a simple health check endpoint",
                    "Use this to verify the application is running and responding",
                    "Useful for monitoring and automated health checks",
                    "Returns OK status when application is healthy"
                })
                .build();
    }

    /**
     * ALL CONCEPTS LISTING - Lists all learning concepts available
     * 
     * This endpoint provides a comprehensive list of all Spring concepts
     * demonstrated in this sample playground.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /demo/concepts
     * PARAMETERS: None
     * 
     * EXAMPLE REQUEST:
     * ✓ http://localhost:8080/demo/concepts
     * 
     * @return ApiResponse with list of all available concepts
     * 
     * EXECUTION FLOW:
     * 1. Client makes HTTP request: GET /demo/concepts
     * 2. Spring routes request to this method
     * 3. Method body executes:
     *    a) Creates a list of concept information
     *    b) Builds ApiResponse with the list
     *    c) Returns ApiResponse (auto-serialized to JSON)
     * 4. Spring serializes the response to JSON
     * 5. Response is sent to client with HTTP 200 (OK)
     * 6. Client receives JSON array of concepts
     * 
     * KEY CONCEPTS DEMONSTRATED:
     * ✓ REST endpoint returning a collection
     * ✓ Structured data format
     * ✓ Discovery of available learning paths
     */
    @GetMapping("/concepts")
    public ApiResponse listAllConcepts() {
        
        // OPERATION: Log method entry
        // WHY: Shows that the API endpoint is being called
        log.info("");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("REQUEST RECEIVED: GET /demo/concepts");
        log.info("Listing all available learning concepts...");
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        
        // OPERATION: Create a list of all concepts with their endpoint details
        // WHY: Provides clients with a discovery mechanism for all available concepts
        java.util.List<java.util.Map<String, String>> concepts = Arrays.asList(
            java.util.Map.of(
                "name", "Dependency Injection (Constructor Injection)",
                "endpoint", "/demo/di",
                "description", "Learn how Spring automatically injects dependencies via constructor",
                "method", "GET",
                "parameters", "name (optional, default: World)",
                "example", "http://localhost:8080/demo/di?name=John"
            ),
            java.util.Map.of(
                "name", "@Bean Annotation",
                "endpoint", "/demo/bean",
                "description", "Learn how to create beans programmatically using @Bean in @Configuration",
                "method", "GET",
                "parameters", "None",
                "example", "http://localhost:8080/demo/bean"
            ),
            java.util.Map.of(
                "name", "Health Check",
                "endpoint", "/demo/health",
                "description", "Simple health check endpoint to verify application is running",
                "method", "GET",
                "parameters", "None",
                "example", "http://localhost:8080/demo/health"
            ),
            java.util.Map.of(
                "name", "All Concepts",
                "endpoint", "/demo/concepts",
                "description", "List all available learning concepts with their endpoints",
                "method", "GET",
                "parameters", "None",
                "example", "http://localhost:8080/demo/concepts"
            )
        );
        
        // OPERATION: Build and return the response
        // WHY: Wraps the concept list in educational information
        log.info(">>> Building response with {} concepts...", concepts.size());
        ApiResponse response = ApiResponse.builder()
                .concept("All Available Concepts")
                .message("Spring Boot Interview Playground - Sample Edition")
                .processFlow("Discovery Endpoint → Lists all concepts → Shows endpoints and descriptions")
                .explanation(Arrays.asList(
                    "This endpoint provides a complete list of all learning concepts available in this playground",
                    "Each concept entry includes: name, endpoint, description, HTTP method, parameters, and example URL",
                    "You can use these URLs to explore each concept",
                    "For detailed learning, visit each endpoint and watch the console logs",
                    "Read the response 'tryout' field for practical learning suggestions",
                    "Each concept is designed to teach one fundamental Spring Boot technique"
                ))
                .data(concepts)
                .tryout(new String[]{
                    "Step 1: Visit this endpoint in your browser or API client",
                    "Step 2: Notice the 'data' field contains a list of all available concepts",
                    "Step 3: Each concept entry has an 'endpoint' field showing its URL",
                    "Step 4: Copy any endpoint URL from the response",
                    "Step 5: Visit that endpoint to learn about that specific concept",
                    "Step 6: Watch the console logs - see how Spring creates and injects beans",
                    "Result: You have a complete learning path for Spring Boot fundamentals!"
                })
                .build();
        
        // OPERATION: Log the response being returned
        // WHY: Shows that the request is being answered
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("RESPONSE READY: {} concepts listed", concepts.size());
        log.info("+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        log.info("");
        
        // OPERATION: Return the response
        // WHY: Spring will serialize this to JSON and send to client
        return response;
    }
}
