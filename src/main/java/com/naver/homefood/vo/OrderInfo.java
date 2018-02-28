package com.naver.homefood.vo;

import java.util.List;

public class OrderInfo {
    private int orderSeq;
    private int boardSeq;
    private String consumerId;
    private char orderStatus;

    private List<OrderFood> orderFoodList;

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public List<OrderFood> getOrderFoodList() {
        return orderFoodList;
    }

    public void setOrderFoodList(List<OrderFood> orderFoodList) {
        this.orderFoodList = orderFoodList;
    }

    public char getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(char orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean validCheck() {
        if(orderFoodList.isEmpty()) {
            return false;
        }
        return true;
    }

}
