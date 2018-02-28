package com.naver.homefood.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naver.homefood.annotation.SellerCheck;
import com.naver.homefood.exception.checkValidException;
import com.naver.homefood.service.OrderService;
import com.naver.homefood.vo.OrderInfo;
/**
 * 음식 주문 API 주문정보 등록 조회
 * @author seonghoon.bae
 *
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 주문정보 등록 : 주문
     * @param orderInfo 주문정보
     * @param id 주문자 id
     * @throws checkValidException 유효성 검사 실패
     */
    @PostMapping(value = "/order")
    public void saveOrderInfo(@RequestBody OrderInfo orderInfo, @CookieValue("id") String id)
        throws checkValidException {
        if (orderInfo.validCheck() == false) {
            throw new checkValidException();
        }
        orderInfo.setConsumerId(id);
        orderService.inputOrderInfo(orderInfo);
    }

    /**
     *주문정보 조회
     * @param consumerId 구매자 id
     * @param page 조회할 페이지
     * @param count 페이지당 주문정보 수
     * @return
     */
    @GetMapping(value = "/order/{consumerId}/{page}/{count}")
    public List<Map<String, Object>> getOrderInfo(@PathVariable String consumerId,
        @PathVariable int page, @PathVariable int count) {
        return orderService.scanOrderInfo(consumerId, page, count);
    }

    /**
     * 주문정보 마지막 페이지
     * @param consumerId 구매자 id
     * @param count 페이지당 주문정보 수
     * @return 마지막 페이지 번호
     */
    @GetMapping(value = "/order/endPage/{consumerId}/{count}")
    public int getEndPage(@PathVariable String consumerId, @PathVariable int count) {
        return orderService.scanEndPage(consumerId, count);
    }

    /**
     * 판매정보 조회
     * @param sellerId 판매자 id
     * @param page 조회할 페이지
     * @param count 페이지당 판매정보 수
     * @return
     */
    @SellerCheck
    @GetMapping(value = "/order/seller/{sellerId}/{page}/{count}")
    public List<Map<String, Object>> getSellerInfo(@PathVariable String sellerId,
        @PathVariable int page, @PathVariable int count) {
        return orderService.scanSellerInfo(sellerId, page, count);
    }

    /**
     * 판매정보 마지막 페이지
     * @param sellerId 판매자 id
     * @param count 페이지당 주문정보 수
     * @return 마지막 페이지번호
     */
    @SellerCheck
    @GetMapping(value = "/order/seller/endPage/{sellerId}/{count}")
    public int getSellerEndPage(@PathVariable String sellerId, @PathVariable int count) {
        return orderService.scanSellerEndPage(sellerId, count);
    }

    /**
     * 주문 승인
     * @param orderSeq 주문정보 번호
     */
    @SellerCheck
    @PutMapping(value = "/order/seller/approve/{orderSeq}")
    public void approveOrder(@PathVariable int orderSeq) {
        orderService.changeOrderStatus(orderSeq);
    }
}
