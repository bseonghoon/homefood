package com.naver.homefood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naver.homefood.exception.checkValidException;
import com.naver.homefood.service.BoardService;
import com.naver.homefood.service.FoodService;
import com.naver.homefood.service.ImageService;
import com.naver.homefood.vo.Board;
import com.naver.homefood.vo.User;
/**
 * 이미지 파일과 음식 목록을 포함하는 게시판의 작성/수정/삭제/조회
 * @author seonghoon.bae
 *
 */
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private FoodService foodService;

    /**
     * 게시글 저장(일반 게시글, 음식리스트, 이미지리스트)
     * @param board 일반 게시글, 음식리스트
     * @param imageFile 이미지 파일
     * @param profile 대표이미지 번호
     * @param id 작성자 id
     * @throws checkValidException 유효성 검사 실패
     */
    @PostMapping(value = "/board")
    public @ResponseBody void saveBoard(@ModelAttribute Board board,
        @RequestParam("imageFile") List<MultipartFile> imageFile,
        @RequestParam int profile, @CookieValue("id") String id)
        throws checkValidException {
        if (board.validCheck() == false) {
            throw new checkValidException();
        }

        board.setSellerId(id);
        boardService.saveBoard(board, imageFile, profile);
    }

    /**
     * 게시글 수정
     * @param board 일반 게시글, 음식리스트
     * @throws checkValidException 유효성 검사 실패
     */
    @PutMapping(value = "/board")
    public void updateBoard(@RequestBody Board board) throws checkValidException {
        if (board.validCheck() == false) {
            throw new checkValidException();
        }
        boardService.updateBoard(board);
    }

    /**
     * 게시글 삭제
     * @param boardSeq 게시판 번호
     */
    @DeleteMapping(value = "/board/{boardSeq}")
    public void deleteBoard(@PathVariable int boardSeq) {
        boardService.deleteBoard(boardSeq);
    }

    /**
     * 게시글 조회
     * @param page 조회 페이지 번호
     * @param count 한페이지당 게시글 개수
     * @return
     */
    @GetMapping(value = "/board/{page}/{count}")
    public List<Board> getBoardList(@PathVariable int page, @PathVariable int count) {
        List<Board> boardList = boardService.getBoardList(page, count);

        return boardList;
    }

    /**
     * 상세 게시글 보기
     * @param boardSeq 게시글 번호
     * @return
     */
    @GetMapping(value = "/board/detail/{boardSeq}")
    public Board getBoardDetail(@PathVariable int boardSeq) {
        return boardService.getBoardDetail(boardSeq);
    }

    /**
     * 페이징을 위한 전체 페이지수
     * @param count 한페이지당 게시물 수
     * @return 전체 페이지수
     */
    @GetMapping(value = "/board/endPage/{count}")
    public int getEndPage(@PathVariable int count) {
        return boardService.getEndPage(count);
    }

    /**
     * 이미지 단건 삭제
     * @param boardSeq 게시글 번호
     * @param number 게시글당 이미지 번호
     */
    @DeleteMapping(value = "/image/one/{boardSeq}/{number}")
    public void removeImageOne(@PathVariable int boardSeq, @PathVariable int number) {
        imageService.deleteImageOne(boardSeq, number);
    }

    /**
     * 음식 단건 삭제
     * @param boardSeq 게시글 번호
     * @param number 게시글당 음식 번호
     */
    @DeleteMapping(value = "/board/food/{boardSeq}/{number}")
    public void removeOneFood(@PathVariable int boardSeq, @PathVariable int number) {
        foodService.deleteOneFood(boardSeq, number);
    }

    /**
     * 주소 조회
     * @param id 조회할 아이디
     * @return
     */
    @GetMapping(value = "/board/writeReady")
    public User getLocation(@CookieValue("id") String id) {
        return boardService.getUserInfo(id);

    }
}
