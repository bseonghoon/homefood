package com.naver.homefood.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.naver.homefood.annotation.SellerCheck;
/**
 * 판매자 권한 확인
 * @author seonghoon.bae
 *
 */
public class SellerCheckInterceptor implements HandlerInterceptor {

    private String notAuth;

    public SellerCheckInterceptor(String notAuth) {
        this.notAuth = notAuth;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
        throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
        throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // TODO Auto-generated method stub
        HandlerMethod method = (HandlerMethod)handler;
        SellerCheck seller = method.getMethodAnnotation(SellerCheck.class);

        if (seller == null) {
            return true;
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("type".equals(cookie.getName()) && "s".equals(cookie.getValue())) {
                    return true;
                }
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + notAuth +"'); location.href= '/';</script>");
        out.flush();

        return false;
    }

}
