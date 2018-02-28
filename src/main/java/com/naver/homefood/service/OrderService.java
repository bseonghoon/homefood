package com.naver.homefood.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.homefood.dao.OrderDao;
import com.naver.homefood.vo.OrderFood;
import com.naver.homefood.vo.OrderInfo;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private FoodService foodService;

    /**
     * 주문정보와 주문된 음식정보 저장
     * @param orderInfo 음식정보를 포함한 주문정보
     */
    @Transactional
    public void inputOrderInfo(OrderInfo orderInfo) {
        orderDao.insertOrderInfo(orderInfo);

        List<OrderFood> orderFoodList = orderInfo.getOrderFoodList();
        for (OrderFood orderFood : orderFoodList) {
            if (orderFood.validCheck()) {
                orderFood.setOrderSeq(orderInfo.getOrderSeq());
                orderDao.insertOrderFood(orderFood);
            }
        }

        foodService.updateFoodCountByOrder(orderInfo.getOrderFoodList(), orderInfo.getBoardSeq());
    }

    public List<Map<String, Object>> scanOrderInfo(String consumerId, int page, int count) {
        Map<String, Object> params = new HashMap<>();
        int offset = count * (page - 1);

        params.put("consumerId", consumerId);
        params.put("offset", offset);
        params.put("count", count);

        return orderDao.selectOrderInfo(params);
    }

    public int scanEndPage(String sellerId, int count) {
        int orderInfoCount = orderDao.selectOrderInfoCount(sellerId);
        int endPage;
        if (orderInfoCount % count == 0) {
            endPage = orderInfoCount / count;
        } else {
            endPage = orderInfoCount / count + 1;
        }

        return endPage;
    }

    public List<Map<String, Object>> scanSellerInfo(String sellerId, int page, int count) {
        Map<String, Object> params = new HashMap<>();
        int offset = count * (page - 1);

        params.put("sellerId", sellerId);
        params.put("offset", offset);
        params.put("count", count);

        return orderDao.selectSellerInfo(params);
    }

    public int scanSellerEndPage(String consumerId, int count) {
        int orderInfoCount = orderDao.selectSellerOrderInfoCount(consumerId);
        int endPage;
        if (orderInfoCount % count == 0) {
            endPage = orderInfoCount / count;
        } else {
            endPage = orderInfoCount / count + 1;
        }

        return endPage;
    }

    public void changeOrderStatus(int orderSeq) {
        orderDao.updateOrderStatus(orderSeq);
    }

}
