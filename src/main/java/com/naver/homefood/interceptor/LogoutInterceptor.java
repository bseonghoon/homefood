package com.naver.homefood.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 로그아웃 처리
 * @author seonghoon.bae
 *
 */
public class LogoutInterceptor implements HandlerInterceptor{

    private static final String cookiePath = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {

        Cookie idCookie = new Cookie("id", "");
        idCookie.setPath(cookiePath);
        idCookie.setMaxAge(0);
        response.addCookie(idCookie);
        Cookie typeCookie = new Cookie("type", "");
        typeCookie.setPath(cookiePath);
        typeCookie.setMaxAge(0);
        response.addCookie(typeCookie);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // TODO Auto-generated method stub

    }

}
