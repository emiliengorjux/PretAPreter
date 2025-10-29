package fr.hiit.pretapreter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Encoder de mots de passe standard
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuration principale de la sécurité HTTP
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactivation CSRF (utile pour tests API via Postman)
                .csrf(AbstractHttpConfigurer::disable)

                // Configuration des règles d’accès
                .authorizeHttpRequests(auth -> auth
                        // GET et POST accessibles à tous
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll());

//                        //  PUT et DELETE nécessitent une authentification
//                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")

        return http.build();
    }
    }

