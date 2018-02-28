package com.naver.homefood.vo;

public class OrderFood {
    private int orderSeq;
    private int foodNumber;
    private int orderCount;

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public boolean validCheck() {
        if(orderCount < 0) {
            return false;
        }
        return true;
    }
}
