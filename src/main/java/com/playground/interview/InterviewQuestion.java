package com.playground.interview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * INTERVIEW QUESTION MODEL - DTO for interview questions
 * 
 * This class represents an interview question related to Spring concepts.
 * It's designed to help developers prepare for Spring interviews by providing:
 * ✓ The concept being tested
 * ✓ The actual interview question
 * ✓ A helpful hint to guide thinking
 * ✓ A link to the demo API that demonstrates the concept
 * 
 * PURPOSE:
 * ✓ Serves as an interactive study tool for Spring interviews
 * ✓ Links theoretical questions to practical demonstrations
 * ✓ Helps developers understand concepts through a Q&A format
 * ✓ Provides a structured learning path: Question → Hint → Demo API
 * 
 * JSON EXAMPLE:
 * {
 *     "concept": "Spring Dependency Injection",
 *     "question": "What is dependency injection and why is it important?",
 *     "hint": "Think about how Spring automatically provides objects instead of you creating them.",
 *     "tryApi": "/demo/di"
 * }
 * 
 * LEARNING WORKFLOW:
 * Step 1: Developer calls /interview/di endpoint
 * Step 2: Developer reads the concept, question, and hint
 * Step 3: Developer thinks about the answer
 * Step 4: Developer calls the tryApi endpoint (e.g., /demo/di)
 * Step 5: Developer observes the Spring behavior in action
 * Step 6: Developer understands the concept through practical demonstration
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewQuestion {
    
    /**
     * The Spring concept being tested
     * EXAMPLES:
     * ✓ "Spring Dependency Injection"
     * ✓ "Spring @Bean Annotation"
     */
    private String concept;
    
    /**
     * The actual interview question
     * EXAMPLES:
     * ✓ "What is dependency injection and why is it important?"
     * ✓ "Explain the @Bean annotation and when you would use it"
     */
    private String question;
    
    /**
     * A helpful hint to guide the developer's thinking
     * EXAMPLES:
     * ✓ "Think about how Spring automatically provides objects..."
     * ✓ "Consider how you can control bean creation programmatically..."
     */
    private String hint;
    
    /**
     * The API endpoint that demonstrates this concept
     * EXAMPLES:
     * ✓ "/demo/di"
     * ✓ "/demo/bean"
     */
    private String tryApi;
    
    /**
     * The complete URL to the demo API (full URL with host and port)
     * EXAMPLES:
     * ✓ "http://localhost:8080/demo/di?name=YourName"
     * ✓ "http://localhost:8080/demo/bean"
     * WHY: Developers can click directly on the link in JSON response
     *      Makes it easier to copy-paste into browser
     *      Shows the full URL with query parameters
     */
    private String tryApiUrl;
}
