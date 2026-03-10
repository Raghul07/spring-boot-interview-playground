package com.playground.interview;

import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * INTERVIEW CONTROLLER - REST endpoints for Spring interview questions
 * 
 * This controller provides an "Interview Question Mode" feature that helps developers
 * prepare for Spring technical interviews.
 * 
 * CONCEPT:
 * ✓ Developers can read interview questions about Spring concepts
 * ✓ Each question provides hints to guide thinking
 * ✓ Each question links to a demo API that shows the concept in action
 * ✓ Response includes BOTH path ("/demo/di") and FULL URL ("http://localhost:8080/demo/di")
 * ✓ This creates an interactive learning experience
 * 
 * LEARNING WORKFLOW:
 * Step 1: Call /interview/{concept} endpoint
 * Step 2: Read the question and hint
 * Step 3: Think about the answer
 * Step 4: Click or copy the tryApiUrl from the response
 * Step 5: Observe the practical demonstration
 * Step 6: Understand the concept through real behavior
 * 
 * WHY BOTH PATH AND FULL URL?
 * ✓ Path (/demo/di): Useful for API routing and cross-deployment flexibility
 * ✓ Full URL (http://localhost:8080/demo/di): Direct link for easy access
 * ✓ Developers can copy-paste the URL directly into browser
 * ✓ No need to manually construct the URL
 * 
 * KEY ANNOTATION: @RestController
 * ✓ Marks this class as a REST controller
 * ✓ Methods return JSON automatically
 * ✓ All responses are serialized using InterviewQuestion
 * 
 * KEY ANNOTATION: @RequestMapping("/interview")
 * ✓ Base path for all endpoints in this controller
 * ✓ Full endpoint paths will be: /interview/{concept}
 * 
 * KEY ANNOTATION: @Slf4j
 * ✓ Provides logging capability for debugging
 * ✓ Helps track which interview questions are accessed
 */
@RestController
@RequestMapping("/interview")
@Slf4j
public class InterviewController {

    /**
     * Helper method to build complete URL
     * 
     * @param request HttpServletRequest (contains server info)
     * @param path The API path (e.g., "/demo/di")
     * @param queryParams Optional query parameters (e.g., "?name=YourName")
     * @return Complete URL (e.g., "http://localhost:8080/demo/di?name=YourName")
     */
    private String buildFullUrl(HttpServletRequest request, String path, String queryParams) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        
        String portPart = (port == 80 || port == 443) ? "" : ":" + port;
        String baseUrl = scheme + "://" + serverName + portPart;
        
        return baseUrl + path + (queryParams != null ? queryParams : "");
    }

    /**
     * DEPENDENCY INJECTION INTERVIEW QUESTION - Test understanding of DI
     * 
     * This endpoint provides an interview question about dependency injection.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /interview/di
     * PARAMETERS: None
     * 
     * EXAMPLE REQUEST:
     * ✓ http://localhost:8080/interview/di
     * 
     * EXAMPLE RESPONSE:
     * {
     *     "concept": "Spring Dependency Injection (Constructor Injection)",
     *     "question": "What is dependency injection and why is it important?",
     *     "hint": "Think about how Spring automatically provides objects...",
     *     "tryApi": "/demo/di",
     *     "tryApiUrl": "http://localhost:8080/demo/di?name=YourName"
     * }
     * 
     * @param request HttpServletRequest to extract server details for full URL
     * @return InterviewQuestion with question, hint, path, and FULL URL
     */
    @GetMapping("/di")
    public InterviewQuestion dependencyInjectionQuestion(HttpServletRequest request) {
        
        log.info("");
        log.info("=====================================================================");
        log.info("[InterviewController] Interview Question Requested: Dependency Injection");
        log.info("[InterviewController] [INFO] This is Interview Mode - read the question");
        log.info("[InterviewController] [INFO] Copy the tryApiUrl and paste in browser");
        log.info("=====================================================================");
        log.info("");
        
        return InterviewQuestion.builder()
                .concept("Spring Dependency Injection (Constructor Injection)")
                .question("What is dependency injection and why is it important? How does Spring do it automatically?")
                .hint("Think about how Spring automatically provides objects to your constructors without you creating them manually. What are the benefits compared to using the 'new' keyword?")
                .tryApi("/demo/di")
                .tryApiUrl(buildFullUrl(request, "/demo/di", "?name=YourName"))
                .build();
    }

    /**
     * @BEAN ANNOTATION INTERVIEW QUESTION - Test understanding of @Bean
     * 
     * This endpoint provides an interview question about the @Bean annotation.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /interview/bean
     * 
     * EXAMPLE REQUEST:
     * ✓ http://localhost:8080/interview/bean
     * 
     * EXAMPLE RESPONSE includes both:
     * - tryApi: "/demo/bean" (path only)
     * - tryApiUrl: "http://localhost:8080/demo/bean" (full URL to click/copy)
     */
    @GetMapping("/bean")
    public InterviewQuestion beanQuestion(HttpServletRequest request) {
        
        log.info("");
        log.info("=====================================================================");
        log.info("[InterviewController] Interview Question Requested: @Bean Annotation");
        log.info("[InterviewController] [INFO] This is Interview Mode - read the question");
        log.info("[InterviewController] [INFO] Copy the tryApiUrl and paste in browser");
        log.info("=====================================================================");
        log.info("");
        
        return InterviewQuestion.builder()
                .concept("Spring @Bean Annotation")
                .question("What is the @Bean annotation used for? When would you use it instead of @Service or @Component?")
                .hint("Think about controlling bean creation programmatically. Consider third-party libraries or beans with complex initialization logic. How would you create beans for them?")
                .tryApi("/demo/bean")
                .tryApiUrl(buildFullUrl(request, "/demo/bean", null))
                .build();
    }

    /**
     * INTERVIEW QUESTIONS INDEX - Lists all available interview questions
     * 
     * This endpoint provides a list of all interview questions available.
     * 
     * HTTP METHOD: GET
     * ENDPOINT: /interview
     */
    @GetMapping
    public java.util.List<java.util.Map<String, String>> allInterviewQuestions(HttpServletRequest request) {
        
        log.info("");
        log.info("=====================================================================");
        log.info("[InterviewController] All Interview Questions Requested");
        log.info("[InterviewController] [INFO] Listing available interview questions");
        log.info("=====================================================================");
        log.info("");
        
        return java.util.Arrays.asList(
            java.util.Map.of(
                "concept", "Spring Dependency Injection",
                "endpoint", "/interview/di",
                "description", "Learn about constructor injection and why it's important",
                "questionUrl", buildFullUrl(request, "/interview/di", null),
                "demoUrl", buildFullUrl(request, "/demo/di", "?name=YourName")
            ),
            java.util.Map.of(
                "concept", "Spring @Bean Annotation",
                "endpoint", "/interview/bean",
                "description", "Learn when and how to use @Bean for programmatic bean creation",
                "questionUrl", buildFullUrl(request, "/interview/bean", null),
                "demoUrl", buildFullUrl(request, "/demo/bean", null)
            )
        );
    }
}
