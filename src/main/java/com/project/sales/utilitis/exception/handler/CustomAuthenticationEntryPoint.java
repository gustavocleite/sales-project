package com.project.sales.utilitis.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(String.format(
                "{ \"status\": %d, " +
                        "\"error\": \"N\\u00E3o autorizado\", " +
                        "\"message\": \"\\u00C9 necess\\u00E1ria autentica\\u00E7\\u00E3o para acessar este recurso.\" }",
                HttpServletResponse.SC_UNAUTHORIZED
        ));
    }
}