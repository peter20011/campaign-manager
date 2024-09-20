package org.example.backend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS)
 * in the Spring Boot application. This allows the React frontend (running
 * on http://localhost:3000) to interact with the backend by specifying allowed
 * origins, HTTP methods, and headers.
 */
@Configuration
public class CorsConfig {   /**
 * Configures CORS mappings for the application.
 *
 * @return a {@link WebMvcConfigurer} that defines the allowed origins, methods,
 * and headers for cross-origin requests.
 */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")  // Allow React frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
