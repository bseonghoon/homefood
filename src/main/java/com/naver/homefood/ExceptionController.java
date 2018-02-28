package com.naver.homefood;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import com.naver.homefood.exception.checkValidException;

@ControllerAdvice
public class ExceptionController{

    @Value("${message.exception.over_upload_size}")
    private String overUploadSize;

    @Value("${message.exception.not_valid}")
    private String notValid;

    @Value("${message.sold_out}")
    private String soldOut;

    /**
     * 유효성검사에서 실패할 경우
     * @return
     */
    @ExceptionHandler(checkValidException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public String valid() {
        return notValid;
    }

    /**
     * 파일 업로드 사이즈가 클 경우
     * @return
     */
    @ExceptionHandler(value = MultipartException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public String maxUploadSize() {
        return overUploadSize;
    }

    /**
     * 주문시 판매수량보다 구매수량이 많을경우
     * @return
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public String soldOut() {
        return soldOut;
    }

}
