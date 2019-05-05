package com.dane.peeper.domain.security;

import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityUserService implements UserDetailsService {
    private final IUserService userService;

    @Autowired
    public SecurityUserService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return getUserDetails(userService.findUserByEmail(username));
        } catch (Exception e) {
            return new org.springframework.security.core.userdetails.User(
                    "User does not exist",
                    "",
                    AuthorityUtils.commaSeparatedStringToAuthorityList("")
            );
        }
    }

    private org.springframework.security.core.userdetails.User getUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.email,
                user.hash,
                getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(user.role.toString());
    }
}
