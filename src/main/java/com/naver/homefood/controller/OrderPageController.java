package com.naver.homefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 주문 페이지 전환
 * @author seonghoon.bae
 *
 */
@Controller
public class OrderPageController {

    /**
     * 주문 페이지
     * @param boardSeq 게시판 번호
     * @return
     */
    @RequestMapping(value = "/order/orderPage/{boardSeq}")
    public String orderPage(Model model, @PathVariable int boardSeq) {
        model.addAttribute("boardSeq", boardSeq);
        return "order/orderPage";
    }

    /**
     * 주문정보 페이지
     * @param id 현재 로그인된 id
     * @return
     */
    @RequestMapping("/order/orderInfoPage")
    public String orderInfoPage(Model model, @CookieValue("id") String id) {
        model.addAttribute("id", id);
        return "order/orderInfoPage";
    }
}
