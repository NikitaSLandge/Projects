package com.example.Farm_management.config;

import com.example.Farm_management.security.CustomUserDetailsService;
import com.example.Farm_management.security.JwtAuthenticationEntryPoint;
import com.example.Farm_management.security.JwtFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Configuration
    @EnableMethodSecurity
    public class SecurityConfig {

       private final CustomUserDetailsService userDetailsService;

        private final JwtAuthenticationEntryPoint authenticationEntryPoint;

        public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint) {
            this.userDetailsService = userDetailsService;
            this.authenticationEntryPoint = authenticationEntryPoint;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public JwtFilter jwtFilter() {
            return new JwtFilter();

        }

        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(userDetailsService);
            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            return daoAuthenticationProvider;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(csrf -> csrf.disable())
                    .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/authenticate").permitAll()
                            .requestMatchers("/api/**").permitAll()
                            .requestMatchers("/api/crops/{name}").permitAll()
                            //.requestMatchers("/**").access(hasIpAddress("192.168.1.22/3"))
                            .anyRequest().authenticated())
                    .authenticationProvider(daoAuthenticationProvider())
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

        //to connect to another pc from same network
      /*  private static AuthorizationManager<RequestAuthorizationContext> hasIpAddress(String ipAddress) {
            IpAddressMatcher ipAddressMatcher = new IpAddressMatcher(ipAddress);
            return (authentication, context) -> {
                HttpServletRequest request = context.getRequest();
                return new AuthorizationDecision(ipAddressMatcher.matches(request));
            };
        }*/


    }



