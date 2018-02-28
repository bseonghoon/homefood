package com.naver.homefood.vo;

import java.util.List;

public class Board {
    private int boardSeq;

    private String sellerId;
    private String title;
    private String content;

    private String location;
    private long postingDate;
    private long updateDate;

    private List<Food> foodList;

    private List<Image> imageList;

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public long getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(long postingDate) {
        this.postingDate = postingDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public boolean validCheck() {
        if(foodList.isEmpty() || title.isEmpty() || location.isEmpty()) {
            return false;
        }
        return true;
    }

}
