package com.naver.homefood.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.naver.homefood.vo.Image;

@Repository
public interface ImageDao {
    void saveImagePath(Map<String, Object> params);

    List<Image> getImage(int boardSeq);

    Image getProfileImagePath(int boardSeq);

    void deleteImage(int boardSeq);

    void deleteImageOne(Map<String, Integer> params);
}
