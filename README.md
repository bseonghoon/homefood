2018년 상반기 NTS 인턴
======================

# 1. 프로젝트명 
 집밥 세끼

## 2. 프로젝트 소개 
 일반 가정집에서 진짜 집밥을 구매하여 먹을 수 있도록 가정집과 구매자의 거래를 도와준다.
 
  판매자인 일반 가정집은 반찬 등의 음식을 가격과 기준gram 수 판매 개수를 함께 게시하면 구매자는 주문을 할 수 있다.

# 3. 기능 목록

#### 경로 이동

<pre>


  /                                 (get)       : 홈화면

  /board/boardWritePage             (get)       : 게시물 작성페이지로 이동
  
  /board/boardList                  (get)       : 게시판 리스트 페이지로 이동
  
  /board/detailPage/{boardSeq}      (get)       : boardSeq의 게시판 페이지로 이동
  
  
  
  
  /order/orderPage/{boardSeq}       (get)       : boardSeq의 주문페이지로 이동
  
  /order/orderInfoPage              (get)       : 주문정보 페이지로 이동




  /signInPage                       (get)       : 로그인 화면: 로그인 화면으로 이동
  
  /signInAction                     (post)      : 로그인 수행: 회원가입이 되어 있으면 로그인 후 홈으로 이동
  
  /signUpPage                       (get)       : 회원가입 화면: 회원가입 화면으로 이동
  
  /signUpAction                     (post)      : 회원가입 수행
  
  /logout                           (get)       : 로그아웃 수행
</pre>
#### Api

<pre>
  /board                            (post)      : 게시글 저장: 이미지 목록과 판매음식 목록도 함께 저장된다.
  
  /board                            (put)       : 게시글 수정: 이미 게시된 글을 수정한다.
  
  /board/{boardSeq}                 (delete)    : 게시글 삭제: boardSeq와 일치하는 게시글을 삭제한다.
  
  /board/{page}/{count}             (get)       : 게시글 리스트: count 개의 page 번째 페이지의 게시판 리스트를 가져온다.
  
  /board/detail/{boardSeq}          (get)       : 게시판 상세: boardSeq와 일치하는 게시글의 정보를 가져온다.
  
  /board/endPage/{count}            (get)       : 마지막 페이지: count개씩 한페이지를 만들었을 때 총 페이지 수
  
  /image/one/{boardSeq}/{number}    (delete)    : 이미지 삭제: 게시물 하나의 여러 이미지중 한가지 이미지를 삭제한다.
  
  /board/food/{boardSeq}/{number}   (delete)    : 음식 삭제: 게시물 하나의 여러 음식죽 한가지 음식을 삭제한다.
  
  /board/writeReady                 (get)       : 회원 주소: 회원이 가입할 때 입력한 주소를 가져온다.
  
  
  
  /order                            (post)      : 주문목록 저장: 구매자가 주문을 수행할 때 주문목록과 구매 음식이 저장된다.
  
  /order/{consumerId}/{page}/{count}(get)       : 구매내역: consumerId의 구매 목록을 count개씩 한페이지를 만들경우 page번째 페이지 리스트
  
  /order/endPage/{consumerId}/{count}(get)      : 구매내역 마지막 페이지: consumerId의 count개씩 한페이지를 만들었을 때 총 페이지 수
  
  /order/{sellerId}/{page}/{count}  (get)       : 판매내역: sellerId의 구매 목록을 count개씩 한페이지를 만들경우 page번째 페이지 리스트
  
  /order/endPage/{sellerId}/{count} (get)       : 판매내역 마지막 페이지: sellerId의 count개씩 한페이지를 만들었을 때 총 페이지 수
  
  /order/seller/approve/{orderSeq}  (put)       : 판매 승인: 판매자가 orderSeq를 가진 주문목록의 주문을 승인한다.
  
  
  /signUp/overlapCheck/{id}         (get)       : id 중복체크: 회원가입시 id를 중복체크 할 수 있다.
  
  </pre>
  
# 4. 기타 제약사항
  파일 업로드는 이미지만을 허용한다.
  
  이미지의 크기는 파일당 1MB로 제한한다.
  
  게시물에는 제목과 주소 1가지 이상의 음식이 포함되어야한다(이미지는 필수 아님)