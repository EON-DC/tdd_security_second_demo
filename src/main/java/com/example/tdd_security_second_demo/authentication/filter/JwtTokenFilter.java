package com.example.tdd_security_second_demo.authentication.filter;

import com.example.tdd_security_second_demo.share.token.JwtSecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    private final JwtSecretKey jwtSecretKey;

    public JwtTokenFilter(UserDetailsService userService, PasswordEncoder passwordEncoder, JwtSecretKey jwtSecretKey) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token.length() > 0) {
            Claims body = Jwts.parser().setSigningKey(jwtSecretKey.getSecretKeyAsByteCode())
                    .parseClaimsJws(token)
                    .getBody();
            String emailSavedOnToken = body.getSubject();
            UserDetails findUser = userService.loadUserByUsername(emailSavedOnToken);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            findUser, null, findUser.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
