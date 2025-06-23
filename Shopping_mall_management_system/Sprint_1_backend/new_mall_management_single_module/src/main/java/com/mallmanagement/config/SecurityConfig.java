package com.mallmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Use the CORS configuration defined in the corsConfigurationSource bean.
            // Spring Security's cors filter is smart enough to handle preflight OPTIONS requests.
            .cors(withDefaults())

            // 2. Disable CSRF protection.
            .csrf(csrf -> csrf.disable())

            // 3. Define authorization rules.
            .authorizeHttpRequests(auth -> auth
                // For now, permit all requests. This ensures no authentication blocks our CORS checks.
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from your Angular application
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        // Allow all standard HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow all headers
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Allow credentials (e.g., cookies, authorization headers)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply this configuration to all paths in your application
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
