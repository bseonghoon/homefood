package com.naver.homefood.vo;

public class Food {
    private int boardSeq;
    private int number; // 하나의 게시글에서 음식 번호

    private String foodName; // 판매하는 음식 이름

    private int count; // 남은 음식 개수
    private int gram; // 음식의 기준 그램수;
    private int price; // 기준 그램수별 단가

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGram() {
        return gram;
    }

    public void setGram(int gram) {
        this.gram = gram;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean validCheck() {
        if(foodName.isEmpty() || count < 0 || gram < 0 || price < 0) {
            return false;
        }
        return true;
    }
}
