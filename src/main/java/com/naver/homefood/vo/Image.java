package com.naver.homefood.vo;

public class Image {
    private int number;
    private int boardSeq;
    private String imagePath;
    private char profile;

    public int getBoardSeq() {
        return boardSeq;
    }

    public void setBoardSeq(int boardSeq) {
        this.boardSeq = boardSeq;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public char getProfile() {
        return profile;
    }

    public void setProfile(char profile) {
        this.profile = profile;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
