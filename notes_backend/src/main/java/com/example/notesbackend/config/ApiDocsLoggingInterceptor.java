package com.example.notesbackend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor to log detailed diagnostic information for API docs endpoints.
 * Captures timing, request details, and errors in a structured format.
 */
@Component
public class ApiDocsLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiDocsLoggingInterceptor.class);
    private static final String START_TIME_ATTR = "apiDocsRequestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        String method = request.getMethod();
        
        // Only log for API docs endpoints
        if (isApiDocsEndpoint(path)) {
            long startTime = System.currentTimeMillis();
            request.setAttribute(START_TIME_ATTR, startTime);
            
            // Add MDC context for structured logging
            MDC.put("path", path);
            MDC.put("method", method);
            MDC.put("remote_addr", request.getRemoteAddr());
            MDC.put("user_agent", request.getHeader("User-Agent"));
            
            logger.info("API_DOCS_REQUEST_START: path={} method={} query={} remote_addr={} user_agent={}", 
                    path, method, request.getQueryString(), request.getRemoteAddr(), 
                    request.getHeader("User-Agent"));
        }
        
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                Object handler, Exception ex) {
        String path = request.getRequestURI();
        
        if (isApiDocsEndpoint(path)) {
            try {
                Long startTime = (Long) request.getAttribute(START_TIME_ATTR);
                long duration = startTime != null ? System.currentTimeMillis() - startTime : -1;
                int status = response.getStatus();
                String method = request.getMethod();
                
                if (ex != null) {
                    // Log error with full exception details
                    logger.error("API_DOCS_REQUEST_ERROR: path={} method={} status={} duration_ms={} " +
                            "exception_type={} exception_message={} stack_trace={}", 
                            path, method, status, duration, 
                            ex.getClass().getName(), ex.getMessage(), getStackTraceAsString(ex));
                } else if (status >= 400) {
                    // Log failures (4xx, 5xx) at ERROR level
                    logger.error("API_DOCS_REQUEST_FAILURE: path={} method={} status={} duration_ms={} " +
                            "remote_addr={}", 
                            path, method, status, duration, request.getRemoteAddr());
                } else {
                    // Log successful requests at INFO level
                    logger.info("API_DOCS_REQUEST_SUCCESS: path={} method={} status={} duration_ms={}", 
                            path, method, status, duration);
                }
            } finally {
                // Clear MDC to avoid leaking context to other requests
                MDC.clear();
            }
        }
    }

    private boolean isApiDocsEndpoint(String path) {
        return path != null && (path.contains("/api-docs") || path.contains("/v3/api-docs"));
    }

    private String getStackTraceAsString(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex.getClass().getName()).append(": ").append(ex.getMessage());
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(" | ").append(element.toString());
            // Limit stack trace to first 5 elements for readability
            if (sb.length() > 500) {
                sb.append(" | ...(truncated)");
                break;
            }
        }
        return sb.toString();
    }
}
