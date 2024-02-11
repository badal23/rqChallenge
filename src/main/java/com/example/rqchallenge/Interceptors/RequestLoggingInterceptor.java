package com.example.rqchallenge.Interceptors;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
public class RequestLoggingInterceptor implements HandlerInterceptor {
    private ThreadLocal<String> requestBodyThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestBodyThreadLocal.remove();
        logRequest(request);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable Exception ex) throws Exception{
        requestBodyThreadLocal.remove();
    }

    private void logRequest(HttpServletRequest request) throws IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        String requestBody = new String(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        requestBodyThreadLocal.set(requestBody);
    }

    public String getRequestBody() {
        return requestBodyThreadLocal.get();
    }



}
