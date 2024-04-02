/*package com.example.Farm_management.security;

import com.example.Farm_management.domain.JwtToken;
import com.example.Farm_management.repository.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;


@Component
public class GenerateToken {

    private Long tokenExp = 86400000L; // Example: 1 day in milliseconds
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public String generateToken(Authentication authentication) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + tokenExp);

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        // Save token to the database
        saveTokenToDatabase(token, authentication.getName(), expirationDate);

        return token;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
        } catch (ExpiredJwtException e) {
            // Handle token expiration
            return false;
        } catch (UnsupportedJwtException e) {
            // Handle unsupported token
            return false;
        } catch (Exception e) {
            // Handle other exceptions
            return false;
        }
        return true;
    }

    public String getUsernameFromToken(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken).getBody();
        return claims.getSubject();
    }

    private void saveTokenToDatabase(String token, String username, Date expirationDate) {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setToken(token);
        jwtToken.setUsername(username);
        jwtToken.setExpirationDate(expirationDate);
        jwtTokenRepository.save(jwtToken);
    }
}
*/



package com.example.Farm_management.security;

import com.example.Farm_management.domain.User;
import com.example.Farm_management.repository.UserRepository;
import com.example.Farm_management.web.exception.JwtExpiredException;
import com.example.Farm_management.web.exception.JwtUnsupportedException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Component
public class GenerateToken {
    
    private final UserRepository userRepository;
    
    private Long tokenExp = 86400000L; //  1 day in milliseconds
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public GenerateToken(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> optionalUser = userRepository.findUserByUsername(userDetails.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Date currentDate = new Date();
            Date expirationDate = new Date(currentDate.getTime() + tokenExp);

            return Jwts.builder()
                    .claim("userId", user.getId())
                    .setSubject(authentication.getName())
                    .setIssuedAt(currentDate)
                    .setExpiration(expirationDate)
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + userDetails.getUsername());
        }
    }
    public boolean validateToken(String authToken) throws JwtUnsupportedException, JwtExpiredException {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Jwt token has expired: " + authToken);
        } catch (UnsupportedJwtException e) {
            throw new JwtUnsupportedException("Unsupported Jwt token: " + authToken);
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt token is not valid: " + authToken);
        }
        return true;
    }
    public String getUsernameFromToken(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken).getBody();
        return claims.getSubject();
    }
    public Long getUserIdFromToken(String authToken) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(authToken).getBody();
        return claims.get("userId", Long.class); // Retrieve user ID from the claims
    }
}




