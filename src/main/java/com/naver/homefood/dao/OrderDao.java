package com.naver.homefood.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.naver.homefood.vo.OrderFood;
import com.naver.homefood.vo.OrderInfo;

@Repository
public interface OrderDao {
    void insertOrderInfo(OrderInfo orderInfo);

    void insertOrderFood(OrderFood orderFood);

    List<Map<String, Object>> selectOrderInfo(Map<String, Object> params);

    int selectOrderInfoCount(String sellerId);

    List<Map<String, Object>> selectSellerInfo(Map<String, Object> params);

    int selectSellerOrderInfoCount(String consumerId);

    void updateOrderStatus(int orderSeq);

}
