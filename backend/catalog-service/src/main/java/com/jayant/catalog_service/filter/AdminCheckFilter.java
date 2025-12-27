package com.jayant.catalog_service.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class AdminCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String method = request.getMethod();

        if ("POST".equals(method) || "PUT".equals(method) || "DELETE".equals(method)) {

            String role = request.getHeader("X-User-Role");

            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admins only");
                return;
            }
        }

        chain.doFilter(req, res);
    }
}