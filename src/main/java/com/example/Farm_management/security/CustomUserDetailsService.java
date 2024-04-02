package com.example.Farm_management.security;
import com.example.Farm_management.domain.User;
import com.example.Farm_management.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findUserByUsername(username);
        Optional<User> userByEmail = userRepository.findUserByEmail(username);
        Optional<User> userByPhoneNumber = userRepository.findUserByPhone(username);

        User user = userByUsername.orElseGet(() -> userByEmail.orElseGet(() -> userByPhoneNumber.orElseThrow(() ->
                        new UsernameNotFoundException("User not found"))));
        return build(user);
    }

    public static org.springframework.security.core.userdetails.User build(User user) {
        Set<GrantedAuthority> authorities = user.getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}



