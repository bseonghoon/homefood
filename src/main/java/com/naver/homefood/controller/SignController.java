package com.naver.homefood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naver.homefood.service.SignService;
import com.naver.homefood.vo.User;
/**
 * 회원 가입및 로그인
 * @author seonghoon.bae
 *
 */
@Controller
public class SignController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SignService signService;

    /**
     * 로그인 페이지
     * @return
     */
    @RequestMapping(value = "/signInPage", method = RequestMethod.GET)
    public String signInPage() {
        return "sign/signInPage";
    }

    /**
     * 로그인
     * @param user 유저 정보
     * @return
     */
    @RequestMapping(value = "/signInAction", method = RequestMethod.POST)
    public ModelAndView signInAction(User user) {

        logger.info("signIn:" + user.getId());
        User signUser = signService.signIn(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", signUser);

        return modelAndView;
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @RequestMapping(value = "/signUpPage", method = RequestMethod.GET)
    public String signUpPage() {
        return "sign/signUpPage";
    }

    /**
     * 회원가입
     * @param user 회원정보
     * @param passwdConfirm 비밀번호 확인
     * @return
     */
    @RequestMapping(value = "/signUpAction", method = RequestMethod.POST)
    public String signUpAction(User user, String passwdConfirm) {
        signService.signUp(user, passwdConfirm);

        return "redirect:/";
    }

    /**
     * 로그아웃
     * @param id 로그아웃된 아이디
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("id") String id) {
        logger.info("logout:" + id);
        return "redirect:/";
    }

    /**
     * id 중복체크
     * @param id 중복체크 할 아이디
     * @return
     */
    @RequestMapping(value = "/signUp/overlapCheck/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean overlapCheck(@PathVariable String id) {
        return signService.overlapCheck(id);
    }
}
