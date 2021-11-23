package com.example.masterservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.masterservice.security.constant.JWTConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(JWTConstants.HEADER_STRING);

        if (header == null || !header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(JWTConstants.TOKEN_PREFIX, "");

        try {
            DecodedJWT jwt = JWT.require(HMAC512(JWTConstants.SECRET.getBytes()))
                    .build()
                    .verify(token);

            String username = jwt.getSubject();
            Claim claim = jwt.getClaim("authorities");
            List<String> authList = claim.asList(String.class);

            Set<GrantedAuthority> authorities = authList.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());

            CurrentUser currentUser = new CurrentUser(username, authorities);
            Authentication auth = new UsernamePasswordAuthenticationToken(currentUser
                        , null, authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }
}
