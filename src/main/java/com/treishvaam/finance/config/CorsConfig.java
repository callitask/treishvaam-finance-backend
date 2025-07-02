package com.treishvaam.finance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a source of Spring bean definitions
public class CorsConfig implements WebMvcConfigurer {

    @Override // Overrides the addCorsMappings method to configure CORS
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS configuration to all API endpoints
                // Define the allowed origins (your frontend domains)
                // IMPORTANT: Ensure these are your exact frontend domains, including HTTPS.
                // If you test locally, add "http://localhost:port" as well.
                .allowedOrigins(
                        "https://udaybhai.com",
                        "https://treishfin.udaybhai.com",
                        "http://localhost:3000"
                )
                // Define the HTTP methods allowed for cross-origin requests
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Define the HTTP headers that can be used in the actual request
                .allowedHeaders("*")
                // Allow the browser to send credentials (like cookies or HTTP authentication headers)
                // with cross-origin requests. Important for JWT if sent as cookies or auth headers.
                .allowCredentials(true);
    }
}