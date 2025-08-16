package com.example.springworkshop.securities;

import com.example.springworkshop.repository.UsersJPARepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersJPARepository usersJPARepository;
    public CustomUserDetailsService(UsersJPARepository usersJPARepository) {
        this.usersJPARepository = usersJPARepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersJPARepository.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
