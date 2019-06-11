package com.dane.peeper.domain.security;

import com.auth0.jwt.JWT;
import com.dane.peeper.domain.models.requestModels.TokenRequestModel;
import com.dane.peeper.domain.models.viewModels.TokenViewModel;
import com.dane.peeper.domain.services.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.dane.peeper.domain.security.TokenConstants.EXPIRATION_TIME;
import static com.dane.peeper.domain.security.TokenConstants.SECRET;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authManager;
    private final IUserService userService;

    public TokenAuthenticationFilter(AuthenticationManager authManager, IUserService userService) {
        this.authManager = authManager;
        this.userService = userService;

        setFilterProcessesUrl("/v1/token");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        TokenRequestModel credentials = new TokenRequestModel();

        try {
            credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), TokenRequestModel.class);
        } catch (IOException e) {
            // This exception will never occur
        }

        return authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.email,
                        credentials.hash,
                        new ArrayList<>())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        String token = null;
        try {
            token = JWT.create()
                    .withSubject(((User) auth.getPrincipal()).getUsername())
                    .withClaim("Id", String.valueOf((this.userService.findUserByEmail(((User) auth.getPrincipal()).getUsername())).id))
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(HMAC512(SECRET.getBytes()));
        } catch (Exception e) {
            logger.error(e);
        }

        res.setContentType("application/json");
        res.getWriter().write(new ObjectMapper().writeValueAsString(new TokenViewModel(token, EXPIRATION_TIME, "Bearer")));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}