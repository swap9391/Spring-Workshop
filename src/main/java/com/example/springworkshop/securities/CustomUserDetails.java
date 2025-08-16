package com.example.springworkshop.securities;

import com.example.springworkshop.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * A custom implementation of Spring Security's {@link UserDetails} interface.
 * <p>
 * This class wraps the application's {@link Users} entity, adapting it to the
 * contract required by Spring Security for authentication and authorization processes.
 * It provides the framework with essential user information like username (email),
 * password, and authorities (roles).
 */
public class CustomUserDetails implements UserDetails {
    private final Users users;

    /**
     * Constructs a new {@code CustomUserDetails} instance.
     *
     * @param users The application's user entity to be wrapped.
     */
    public CustomUserDetails(Users users) {
        this.users = users;
    }


    /**
     * Returns the authorities granted to the user. In this implementation, it maps
     * the single role from the {@link Users} object to a {@link SimpleGrantedAuthority}.
     *
     * @return a collection of the user's authorities, never {@code null}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+users.getRole()));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return users.getPassword();
    }

    /**
     * Returns the username used to authenticate the user. In this application,
     * the user's email is used as the unique identifier for the username.
     *
     * @return the user's email.
     */
    @Override
    public String getUsername() {
        return users.getEmail();
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return {@code true} as this implementation does not handle account expiration.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return {@code true} as this implementation does not handle account locking.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return {@code true} as this implementation does not handle credential expiration.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return {@code true} as this implementation assumes all users are enabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
