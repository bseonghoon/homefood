package com.naver.homefood.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naver.homefood.dao.BoardDao;
import com.naver.homefood.vo.Board;
import com.naver.homefood.vo.Food;
import com.naver.homefood.vo.Image;
import com.naver.homefood.vo.User;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    @Autowired
    private ImageService imageService;

    @Autowired
    private FoodService foodService;

    /**
     * 일반 게시글, 이미지리스트, 음식리스트 저장
     * @param board 게시판 번호
     * @param imageFile 이미지 파일리스트
     * @param profile 대표이미지 번호
     */
    @Transactional
    public void saveBoard(Board board, List<MultipartFile> imageFile, int profile) {

        board.setPostingDate(System.currentTimeMillis());
        board.setUpdateDate(System.currentTimeMillis());
        boardDao.saveBoard(board);

        List<Food> foodList = board.getFoodList();
        for (Food food : foodList) {
            food.setBoardSeq(board.getBoardSeq());
        }
        foodService.saveFood(foodList);

        imageService.saveImage(imageFile, board.getBoardSeq(), profile);
    }

    /**
     * 대표이미지를 포함한 게시판 목록 조회
     * @param page 조회할 페이지
     * @param count 한페이지당 게시글 개수
     * @return
     */
    @Transactional
    public List<Board> getBoardList(int page, int count) {
        Map<String, Integer> param = new HashMap<>();

        int offset = count * (page - 1);
        param.put("offset", offset);
        param.put("count", count);

        List<Board> boardList = boardDao.getBoardList(param);

        for (Board board : boardList) {
            Image profileImage = imageService.getProfileImage(board.getBoardSeq());
            List<Image> profileImageList = Arrays.asList(profileImage);
            board.setImageList(profileImageList);
        }

        return boardList;
    }

    @Transactional
    public void updateBoard(Board board) {
        board.setUpdateDate(System.currentTimeMillis());
        boardDao.updateBoard(board);
        foodService.updateFood(board.getFoodList());
    }

    @Transactional
    public void deleteBoard(int boardSeq) {
        boardDao.deleteBoard(boardSeq);

        foodService.deleteFood(boardSeq);
        imageService.deleteImage(boardSeq);
    }

    @Transactional
    public Board getBoardDetail(int boardSeq) {
        Board board = boardDao.getBoardDetail(boardSeq);
        board.setFoodList(foodService.getFood(boardSeq));
        board.setImageList(imageService.getImagePath(boardSeq));
        return board;
    }

    public int getEndPage(int count) {
        int boardCount = boardDao.getBoardCount(count);
        int endPage;
        if (boardCount % count == 0) {
            endPage = boardCount / count;
        } else {
            endPage = boardCount / count + 1;
        }

        return endPage;
    }

    public User getUserInfo(String id) {
        return boardDao.selectUserInfo(id);
    }

}
