package com.example.demo.config.filter;

import com.example.demo.service.UserService;
import com.google.common.net.HttpHeaders;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nullable;
import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String key;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
        }
    }
}
