package com.epam.hw3.interceptor;

import com.epam.hw3.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Component
public class LogInterceptor implements HandlerInterceptor {

    Logger logger;

    public LogInterceptor() {
        logger = LoggerFactory.getLogger(LogInterceptor.class);;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer id = (Integer) request.getSession().getAttribute("id");
        LocalDateTime startTime = LocalDateTime.now();

        logger.info("LogInterceptor preHandle:");
        logger.info("User with id='" + id + "' go to " + request.getRequestURL());
        logger.info("Status - " + response.getStatus());
        logger.info("StartTime - " + startTime);
        request.setAttribute("startTime", startTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Integer id = (Integer) request.getSession().getAttribute("id");
        LocalDateTime endTime = LocalDateTime.now();

        logger.info("LogInterceptor afterCompletion:");
        logger.info("User with id='" + id + "' go to " + request.getRequestURL());
        logger.info("Status - " + response.getStatus());
        logger.info("EndTime - " + endTime);
    }
}
