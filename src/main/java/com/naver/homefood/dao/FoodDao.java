package com.naver.homefood.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.naver.homefood.vo.Food;

@Repository
public interface FoodDao {
    void insertFood(Food food);

    List<Food> selectFood(int boardSeq);

    void deleteFood(int boardSeq);

    void deleteOneFood(Map<String, Integer> params);

    void updateFood(Food food);

    void updateFoodCountByOrder(Food food);
}
