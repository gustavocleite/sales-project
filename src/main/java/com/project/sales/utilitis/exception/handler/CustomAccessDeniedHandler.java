package com.project.sales.utilitis.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write(String.format(
                "{ \"status\": %d," +
                        " \"error\": \"Acesso negado\", " +
                        "\"message\": \"Voc\\u00EA n\\u00E3o tem permiss\\u00E3o para acessar este recurso.\" }",
                HttpServletResponse.SC_FORBIDDEN
        ));
    }
}
