package com.naver.homefood.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.naver.homefood.vo.Board;
import com.naver.homefood.vo.User;

@Repository
public interface BoardDao {
    void saveBoard(Board board);

    List<Board> getBoardList(Map<String, Integer> param);

    void deleteBoard(int boardSeq);

    void updateBoard(Board board);

    Board getBoardDetail(int boardSeq);

    int getBoardCount(int count);

    User selectUserInfo(String id);
}
