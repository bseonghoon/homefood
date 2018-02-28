package com.naver.homefood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.naver.homefood.annotation.SellerCheck;
/**
 * 게시판 페이지 전환
 * @author seonghoon.bae
 *
 */
@Controller
public class BoardPageController {

    /**
     * 게시글 작성 페이지
     * @return
     */
    @SellerCheck
    @RequestMapping(value = "/board/boardWritePage", method = RequestMethod.GET)
    public String boardWritePage() {
        return "board/boardWritePage";
    }

    /**
     * 게시글 리스트
     * @return
     */
    @RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
    public String boardList() {
        return "board/boardList";
    }

    /**
     * 게시글 상세페이지
     * @param boardSeq 게시글 번호
     * @param id 사용자 아이디(게시글 수정 삭제권한 확인)
     * @return
     */
    @RequestMapping(value = "/board/detailPage/{boardSeq}", method = RequestMethod.GET)
    public String boardDetailPage(Model model, @PathVariable int boardSeq,@CookieValue("id") String id) {
        model.addAttribute("id", id);
        model.addAttribute("boardSeq", boardSeq);
        return "board/boardDetail";
    }
}
