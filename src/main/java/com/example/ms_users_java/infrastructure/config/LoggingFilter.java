package com.example.ms_users_java.infrastructure.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        // Cast ServletRequest to HttpServletRequest to access HTTP-specific methods
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // Log the request details
        logger.info("Incoming request: {} {} from {}",
                    httpRequest.getMethod(),
                    httpRequest.getRequestURI(),
                    httpRequest.getRemoteAddr());

        // Proceed with the next filter in the chain
        filterChain.doFilter(servletRequest, servletResponse);

        long duration = System.currentTimeMillis() - startTime;

        // Cast ServletResponse to HttpServletResponse to access HTTP-specific methods
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Log the response details
        logger.info("Outgoing response: {} {} in {} ms",
                    httpResponse.getStatus(),
                    httpRequest.getRequestURI(),
                    duration);
    }
}
