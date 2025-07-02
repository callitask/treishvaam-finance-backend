package com.treishvaam.finance.security;

import com.treishvaam.finance.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry; // Import for CorsConfig if it's in SecurityConfig
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Import for CorsConfig if it's in SecurityConfig


@Configuration // Marks this class as a source of bean definitions
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService; // Service to load user-specific data

    @Bean // Defines a bean for password encoding
    public PasswordEncoder passwordEncoder() {
        // Uses BCrypt algorithm for strong password hashing
        return new BCryptPasswordEncoder();
    }

    @Bean // Defines a bean for the Authentication Manager
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Retrieves the AuthenticationManager from the provided AuthenticationConfiguration
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean // Configures the security filter chain (main security rules)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (Cross-Site Request Forgery) protection for stateless APIs,
            // as JWT tokens typically handle this for REST services.
            .csrf(csrf -> csrf.disable())
            // Configure authorization rules for HTTP requests
            .authorizeHttpRequests(authorize -> authorize
                // Allow specific endpoints to be accessed without any authentication (public access)
                .requestMatchers("/api/auth/test").permitAll() // For your test endpoint
                .requestMatchers("/api/auth/register", "/api/auth/login", "/api/auth/verify").permitAll() // Registration, Login, and Email Verification
                // Any other request requires authentication
                .anyRequest().authenticated()
            )
            // Configure session management to be stateless, essential for JWT-based authentication.
            // This means no HTTP session will be created or used by Spring Security.
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // If you have CorsConfig as a separate @Configuration bean, it will be applied automatically.
        // If you need to configure CORS directly within SecurityConfig (less common for global config),
        // you would add it here, e.g.:
        // .cors(cors -> cors.disable()); // Or cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    // The @Autowired configureGlobal method often causes circular dependency issues
    // with modern Spring Security configurations when other beans (like PasswordEncoder,
    // AuthenticationManager, UserDetailsService) are also defined as @Bean methods.
    // Spring Security typically auto-wires these dependencies correctly without it.
    // Ensure this method is REMOVED from your SecurityConfig.java file.
}