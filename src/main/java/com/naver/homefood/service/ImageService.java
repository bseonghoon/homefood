package com.naver.homefood.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naver.homefood.dao.ImageDao;
import com.naver.homefood.vo.Image;

@Service
public class ImageService {

    @Value("${file.image.save-path}")
    public String path;

    @Autowired
    private ImageDao imageDao;

    /**
     * 이미지 파일 업로드
     * @param imageFileList 이미지파일 리스트
     * @param boardSeq 게시판 번호
     * @param profileNumber 대표이미지 번호
     */
    public void saveImage(List<MultipartFile> imageFileList, int boardSeq, int profileNumber) {
        String thisBoardDir =  path + "seq" + boardSeq + File.separator;

        makeDirectory(thisBoardDir);
        int imageNumber = 1;
        for(int imageIndex = 0; imageIndex < imageFileList.size(); imageIndex++) {
            MultipartFile imageFile = imageFileList.get(imageIndex);
            String fileName = System.currentTimeMillis() + imageFile.getOriginalFilename();
            String imagePath = thisBoardDir + File.separator + fileName;
            //파일 저장에 실패할 경우 DB에 저장 하지 않는다.
            if (writeFile(imageFile, imagePath)) {
                Map<String, Object> params = new HashMap<>();
                params.put("imagePath", imagePath);
                params.put("boardSeq", boardSeq);
                char profile = (imageIndex == profileNumber ? 'y' : 'n') ;
                params.put("profile", profile);
                params.put("number", imageNumber++);

                imageDao.saveImagePath(params);
            }
        }
    }

    /**
     * 이미지가 저장될 디렉토리 생성
     * @param dirPath
     */
    private void makeDirectory(String dirPath) {
        File targetDir = new File(dirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
    }

    /**
     * 파일 저장
     * @param imageFile 이미지 파일
     * @param imagePath 저장될 주소
     * @return
     */
    private boolean writeFile(MultipartFile imageFile, String imagePath) {

        try {
            File image = new File(imagePath);
            imageFile.transferTo(image);
            if (ImageIO.read(image) == null) {//image가 아닐 경우 null을 리턴
                image.delete();
                return false;
            }
            return true;
        } catch (IOException e) {
            e.getStackTrace();
        }
        return false;
    }

    public Image getProfileImage(int boardSeq) {
        return imageDao.getProfileImagePath(boardSeq);
    }

    public List<Image> getImagePath(int boardSeq) {
        return imageDao.getImage(boardSeq);
    }

    public void deleteImage(int boardSeq) {
        String dirPath = path + "seq" + boardSeq + File.separator;
        File dir = new File(dirPath);

        File[] images = dir.listFiles();
        for (File image : images) {
            image.delete();
        }
        dir.delete();

        imageDao.deleteImage(boardSeq);
    }

    public void deleteImageOne(int boardSeq, int number) {
        Map<String, Integer> params = new HashMap<>();
        params.put("boardSeq", boardSeq);
        params.put("number", number);

        imageDao.deleteImageOne(params);
    }
}
