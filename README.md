# 🎓 Spring Boot Interview Playground - Free Sample Project


A **simplified Spring Boot 3 Maven project** that demonstrates the two foundational Spring concepts through REST APIs with **detailed logging at every step**.

> **Sample Edition**: This is a simplified version focusing on the essentials: **@Bean Annotation** and **Dependency Injection**. Perfect for beginners to understand Spring fundamentals.

---

## 📚 What You'll Learn (2 Core Concepts)

This sample playground teaches you the **foundation** of Spring:

1. **@Bean Annotation** - How to create beans programmatically with `@Configuration`
2. **Constructor Injection** - How Spring automatically injects dependencies into your code

**✨ Each concept has its own REST endpoint with detailed JSON responses and console logs showing exactly what Spring does!**

---

### ✅ System Prerequisites (Required)

Before you begin, ensure you have these installed:

| Requirement | Version | Download Link |
|---|---|---|
| **Java JDK** | 17 or higher | [Java 21 LTS](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptopenjdk.net/) |
| **Maven** | 3.6 or higher | [Maven Download](https://maven.apache.org/download.cgi) |
| **IDE** | Any one of below | VS Code, IntelliJ, Eclipse |

#### ✅ Verify Installation

After installing, verify in your terminal/command prompt:

```bash
# Check Java version
java -version
# Expected: Java 17+ or 21

# Check Maven version
mvn -version
# Expected: Maven 3.6 or higher
```

---

## 🚀 Quick Start (3 steps)

### Step 1: Open Terminal & Navigate to Project

```bash
cd d:\Java_lab\spring-boot-interview-playground-sample
```

### Step 2: Build the Project

```bash
mvn clean install
```

### Step 3: Run the Application

**Option A: Using Maven**
```bash
mvn spring-boot:run
```

**Option B: Using Java (after build)**
```bash
java -jar target/spring-boot-interview-playground-sample-1.0.0.jar
```

**Expected Output:**
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.3)

...
Tomcat started on port(s): 8080
Started SpringBootPlaygroundApp in X.XX seconds
```

✅ **Application is now running!**

---

## 📡 API Endpoints

All endpoints are available at: `http://localhost:8080/demo/`

### 1️⃣ Dependency Injection Demo
```
GET http://localhost:8080/demo/di?name=John
```

**What it does:**
- Demonstrates how Spring injects the GreetingService automatically
- Shows constructor injection in action
- Logs every step of the process

**Example Response:**
```json
{
  "concept": "Dependency Injection (Constructor Injection)",
  "message": "GreetingService successfully injected by Spring!",
  "processFlow": "Spring creates @Service → Injects into @RestController → Method uses it",
  "explanation": [
    "1. DemoController constructor declares a GreetingService parameter",
    "2. Spring sees this constructor parameter",
    "3. Spring searches in Application Context for a GreetingService bean",
    "4. Spring finds GreetingServiceImpl (marked with @Service)",
    "5. Spring creates an instance of GreetingServiceImpl",
    "6. Spring calls the constructor with this instance",
    "7. Now we can use this.greetingService without creating it ourselves",
    "8. This is called CONSTRUCTOR INJECTION - the best practice",
    "9. No @Autowired needed - Spring detects it from constructor signature!"
  ],
  "data": "Hello, John!",
  "tryout": [
    "Step 1: Open DemoController.java in your editor",
    "Step 2: Look at the constructor - it has GreetingService parameter",
    "Step 3: Notice there's NO @Autowired annotation needed",
    "Step 4: Spring automatically injects GreetingService from constructor parameter",
    "Step 5: The greet() method is called from this.greetingService",
    "Step 6: Watch the logs - you'll see 'greetingService.greet()' being called",
    "Result: This is DEPENDENCY INJECTION in action!"
  ]
}
```

**Try these:**
- `http://localhost:8080/demo/di` (uses default: "World")
- `http://localhost:8080/demo/di?name=Alice`
- `http://localhost:8080/demo/di?name=Bob`

---

### 2️⃣ @Bean Annotation Demo
```
GET http://localhost:8080/demo/bean
```

**What it does:**
- Demonstrates how @Bean creates beans programmatically
- Shows the BeanFactory configuration class in action
- Logs how the bean is created and stored

**Example Response:**
```json
{
  "concept": "@Bean Annotation",
  "message": "Bean successfully created and injected by Spring!",
  "processFlow": "@Configuration detected → @Bean method called → Instance created → Stored in Application Context",
  "explanation": [
    "1. BeanFactory class is marked with @Configuration",
    "2. BeanFactory contains a method marked with @Bean",
    "3. At startup, Spring detects @Bean annotation",
    "4. Spring calls the welcomeMessage() method",
    "5. A String object is created and returned",
    "6. Spring stores this String in the Application Context",
    "7. When DemoController constructor executes, Spring injects this bean",
    "8. Now we can use this.welcomeMessage without creating it",
    "9. @Bean is useful for: third-party library beans, complex configurations"
  ],
  "data": "Welcome to Spring Boot Interview Playground - Sample Edition!",
  "tryout": [
    "Step 1: Open BeanFactory.java in your editor",
    "Step 2: Look at the welcomeMessage() method - notice @Bean annotation",
    "Step 3: The method name 'welcomeMessage' becomes the bean ID/name",
    "Step 4: See how method creates String object and returns it",
    "Step 5: Open DemoController.java - this bean is injected in constructor",
    "Step 6: Watch the logs - look for 'BeanFactory CONSTRUCTOR' and bean creation",
    "Result: You'll see how @Bean gives full control over bean creation!"
  ]
}
```

---

### 3️⃣ Health Check
```
GET http://localhost:8080/demo/health
```

**What it does:**
- Simple health check to verify the application is running

**Example Response:**
```json
{
  "concept": "Health Check",
  "message": "Application is running",
  "processFlow": "Health Check Endpoint",
  "explanation": "Simple health check endpoint to verify application is running",
  "data": "OK",
  "tryout": [
    "This is a simple health check endpoint",
    "Use this to verify the application is running and responding",
    "Useful for monitoring and automated health checks",
    "Returns OK status when application is healthy"
  ]
}
```

---

### 4️⃣ List All Concepts
```
GET http://localhost:8080/demo/concepts
```

**What it does:**
- Displays all available learning concepts
- Shows endpoint URLs for each concept
- Provides descriptions and example requests
- Acts as a discovery/navigation endpoint

**Example Response:**
```json
{
  "concept": "All Available Concepts",
  "message": "Spring Boot Interview Playground - Sample Edition",
  "processFlow": "Discovery Endpoint → Lists all concepts → Shows endpoints and descriptions",
  "explanation": [
    "This endpoint provides a complete list of all learning concepts available in this playground",
    "Each concept entry includes: name, endpoint, description, HTTP method, parameters, and example URL",
    "You can use these URLs to explore each concept",
    "For detailed learning, visit each endpoint and watch the console logs",
    "Read the response 'tryout' field for practical learning suggestions",
    "Each concept is designed to teach one fundamental Spring Boot technique"
  ],
  "data": [
    {
      "name": "Dependency Injection (Constructor Injection)",
      "endpoint": "/demo/di",
      "description": "Learn how Spring automatically injects dependencies via constructor",
      "method": "GET",
      "parameters": "name (optional, default: World)",
      "example": "http://localhost:8080/demo/di?name=John"
    },
    {
      "name": "@Bean Annotation",
      "endpoint": "/demo/bean",
      "description": "Learn how to create beans programmatically using @Bean in @Configuration",
      "method": "GET",
      "parameters": "None",
      "example": "http://localhost:8080/demo/bean"
    },
    {
      "name": "Health Check",
      "endpoint": "/demo/health",
      "description": "Simple health check endpoint to verify application is running",
      "method": "GET",
      "parameters": "None",
      "example": "http://localhost:8080/demo/health"
    },
    {
      "name": "All Concepts",
      "endpoint": "/demo/concepts",
      "description": "List all available learning concepts with their endpoints",
      "method": "GET",
      "parameters": "None",
      "example": "http://localhost:8080/demo/concepts"
    }
  ],
  "tryout": [
    "Step 1: Visit this endpoint in your browser or API client",
    "Step 2: Notice the 'data' field contains a list of all available concepts",
    "Step 3: Each concept entry has an 'endpoint' field showing its URL",
    "Step 4: Copy any endpoint URL from the response",
    "Step 5: Visit that endpoint to learn about that specific concept",
    "Step 6: Watch the console logs - see how Spring creates and injects beans",
    "Result: You have a complete learning path for Spring Boot fundamentals!"
  ]
}
```

---

## 📊 Monitor the Logs

When you run the application, watch the **console logs** to see:

```
[GreetingServiceImpl] Constructor is being called by Spring
[GreetingServiceImpl] Bean is being created by Spring
[OK] BeanFactory CONSTRUCTOR - Configuration class is being created
[OK] Spring will call these methods to create beans
[DemoController] Constructor is being called by Spring
[DemoController] Dependencies are being injected:
    ✓ GreetingService injected
    ✓ String (welcomeMessage) injected
```

These logs show you **exactly what Spring is doing** at startup!

---

## 🏗️ Project Structure

```
spring-boot-interview-playground-sample/
├── src/
│   └── main/
│       ├── java/com/playground/
│       │   ├── SpringBootPlaygroundApp.java       (Main entry point)
│       │   ├── controller/
│       │   │   └── DemoController.java            (REST endpoints)
│       │   ├── service/
│       │   │   ├── GreetingService.java           (Service interface)
│       │   │   └── GreetingServiceImpl.java        (Service implementation)
│       │   ├── beans/
│       │   │   └── BeanFactory.java               (@Bean configuration)
│       │   ├── dto/
│       │   │   └── ApiResponse.java               (Response format)
│       │   └── exception/
│       │       ├── CustomException.java
│       │       ├── ErrorResponse.java
│       │       └── GlobalExceptionHandler.java    (Centralized error handling)
│       └── resources/
│           └── application.properties
├── pom.xml                                        (Maven dependencies)
└── README.md
```

---

## 💡 Key Concepts Explained

### 1. @Bean Annotation
The `@Bean` annotation is used in `@Configuration` classes to create beans programmatically:

```java
@Configuration
public class BeanFactory {
    @Bean
    public String welcomeMessage() {
        return "Welcome to Spring Boot!";
    }
}
```

**When to use @Bean:**
- ✓ Creating beans for third-party libraries (RestTemplate, DataSource, etc.)
- ✓ Complex bean initialization logic
- ✓ Multiple beans of the same type with different configurations
- ✓ Conditional bean creation

---

### 2. Dependency Injection (Constructor Injection)
Constructor injection is the best way to inject dependencies:

```java
@RestController
public class DemoController {
    private final GreetingService greetingService;
    
    // Spring automatically injects dependencies via constructor
    public DemoController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
```

**Why constructor injection is best:**
- ✓ Dependencies are immutable (final)
- ✓ Dependencies cannot be null
- ✓ Dependencies are visible in the constructor signature
- ✓ Easy to test (can create instances manually)
- ✓ Spring automatically handles injection

---

## 🔍 Swagger UI

**Live API Documentation is available at:**
```
http://localhost:8080/swagger-ui.html
```

This shows:
- All available endpoints
- Request/response formats
- Try-it-out functionality
- API documentation

---

## 🛠️ Common Commands

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Run tests (if added)
mvn test

# Package as JAR
mvn package

# Run the JAR
java -jar target/spring-boot-interview-playground-sample-1.0.0.jar

# View dependencies
mvn dependency:tree
```

---

## 📝 Troubleshooting

### Port 8080 Already in Use
If port 8080 is already in use, change it in `application.properties`:
```properties
server.port=8081
```

### Java Version Issues
Make sure you have Java 17 or higher:
```bash
java -version
```

### Maven Not Found
Add Maven to your PATH environment variable, or use the full path to Maven.

---

## 📚 Learning Path

1. **Start here:** Run the application and watch the logs
2. **Call the endpoints:** Use Swagger UI or curl
3. **Read the logs:** See Spring creating beans and injecting dependencies
4. **Read the code comments:** Extensive comments explain what's happening
5. **Understand the concepts:** @Bean and Constructor Injection
6. **Experiment:** Modify the code and see what happens

---

## Upcoming Concepts

More concepts will be added in the full version:

• @Qualifier  
• Bean Scope  
• @Primary  
• Lazy Initialization  
• Spring Profiles  
• AOP  

---

## Full Version (Coming Soon)

The full version will include:

• 20+ Spring interview concepts  
• Advanced runnable examples  
• Detailed explanations  

The full product will be released on **Gumroad**.

---

## Why this project?

Instead of memorizing answers, developers can **experiment and learn Spring concepts by running APIs**.


## ❓ FAQ

**Q: How does Spring know which bean to inject?**
A: Spring looks at the type of the constructor parameter (e.g., `GreetingService`) and searches for a matching bean in the Application Context. It finds `GreetingServiceImpl` which implements `GreetingService`.

**Q: What if there are multiple beans of the same type?**
A: Use `@Qualifier("beanName")` to specify which one to inject (not shown in sample to keep it simple).

**Q: When are beans created?**
A: By default, Spring creates beans at startup (singleton scope). You can change this with `@Scope`.

**Q: Can I use field injection instead of constructor injection?**
A: Technically yes with `@Autowired`, but constructor injection is the best practice because it makes dependencies explicit and immutable.

---

---

## ⭐ Support the Project

If you find this project useful, please consider giving it a ⭐ on GitHub.

It helps other developers discover the project.

---

## Contributions

Contributions and suggestions are welcome.
Feel free to open issues or submit pull requests.

---

## Star History

If this project helps you understand Spring better, please give it a star ⭐.

## 📄 License

This is a learning project for educational purposes.

---

