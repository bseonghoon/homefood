package com.naver.homefood.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.homefood.dao.FoodDao;
import com.naver.homefood.vo.Food;
import com.naver.homefood.vo.OrderFood;

@Service
public class FoodService {

    @Autowired
    private FoodDao foodDao;

    public void saveFood(List<Food> foodList) {
        int number = 1;
        for (Food food : foodList) {
            if (food.validCheck()) {
                food.setNumber(number);
                number++;
                foodDao.insertFood(food);
            }
        }
    }

    public List<Food> getFood(int boardSeq) {
        return foodDao.selectFood(boardSeq);
    }

    public void deleteFood(int boardSeq) {
        foodDao.deleteFood(boardSeq);
    }

    public void deleteOneFood(int boardSeq, int number) {

        Map<String, Integer> params = new HashMap<>();
        params.put("boardSeq", boardSeq);
        params.put("number", number);

        foodDao.deleteOneFood(params);
    }

    public void updateFood(List<Food> foodList) {
        for (Food food : foodList) {
            foodDao.updateFood(food);
        }
    }

    public void updateFoodCountByOrder(List<OrderFood> foodList, int boardSeq) {
        Food food = new Food();
        for (OrderFood orderfood : foodList) {
            if (orderfood.getOrderCount() > 0) {
                food.setBoardSeq(boardSeq);
                food.setCount(orderfood.getOrderCount());
                food.setNumber(orderfood.getFoodNumber());
                foodDao.updateFoodCountByOrder(food);
            }
        }
    }
}
