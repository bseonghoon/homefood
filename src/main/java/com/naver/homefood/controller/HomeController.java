package com.naver.homefood.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 메인 화면
 * @author seonghoon.bae
 *
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale, Model model, @CookieValue(required = false) String id) {
        logger.info("homefood");
        boolean loginStatus = true;
        if(id == null) {
            loginStatus = false;
        }

        model.addAttribute("loginStatus", loginStatus);

        return "home";
    }
}
