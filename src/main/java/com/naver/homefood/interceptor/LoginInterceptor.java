package com.naver.homefood.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.naver.homefood.vo.User;
/**
 * 로그인 처리
 * @author seonghoon.bae
 *
 */
public class LoginInterceptor implements HandlerInterceptor{

    private static final String cookiePath = "/";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        Map<String, Object> model = modelAndView.getModel();
        User user = (User)model.get("user");

        if (user == null) {
            modelAndView.setViewName("redirect:/signInPage");
            return;
        }

        Cookie idCookie = new Cookie("id", user.getId());
        idCookie.setPath(cookiePath);
        idCookie.setMaxAge(-1);
        response.addCookie(idCookie);
        Cookie typeCookie = new Cookie("type", user.getType() + "");
        typeCookie.setPath(cookiePath);
        typeCookie.setMaxAge(-1);
        response.addCookie(typeCookie);

        modelAndView.setViewName("redirect:/");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // TODO Auto-generated method stub

    }

}
