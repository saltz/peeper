package com.dane.peeper.config;

import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.security.TokenAuthenticationFilter;
import com.dane.peeper.domain.security.TokenAuthorizationFilter;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Set;

import static com.dane.peeper.domain.security.TokenConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private IUserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurity(IUserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new TokenAuthenticationFilter(authenticationManager()))
                .addFilter(new TokenAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                final User user = userService.findUserByEmail(username);
                return createSpringSecurityUser(user);
            } catch (Exception e) {
                return new org.springframework.security.core.userdetails.User("user does not exists", "",
                        Collections.singleton(new SimpleGrantedAuthority("Unauthorized")));
            }
        }).passwordEncoder(bCryptPasswordEncoder);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        final Set<SimpleGrantedAuthority> roles = Collections.singleton(new SimpleGrantedAuthority(user.role.toString()));
        return new org.springframework.security.core.userdetails.User(user.email, user.hash, roles);
    }
}