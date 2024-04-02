package com.example.Farm_management.security;

import com.example.Farm_management.web.exception.JwtExpiredException;
import com.example.Farm_management.web.exception.JwtUnsupportedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

    @Component
    public class JwtFilter extends OncePerRequestFilter {
        @Autowired
        private  GenerateToken generateToken;
        @Autowired
        private  CustomUserDetailsService userDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            try {
                String token = getBearerToken(request);

                if (token != null && generateToken.validateToken(token)) {
                    String username = generateToken.getUsernameFromToken(token);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (userDetails != null) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            catch (JwtExpiredException e) {
                throw new RuntimeException("Jwt token has expired");
            } catch (JwtUnsupportedException e) {
                throw new RuntimeException("Jwt is not supporting");}
            filterChain.doFilter(request, response);
        }

        private String getBearerToken(HttpServletRequest httpServletRequest) {
            String bearerToken = httpServletRequest.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            } else {
                return null;
            }
        }
    }



