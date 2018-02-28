<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>HOMEFOOD</title>
<style type="text/css">
.div_square {
	position: absolute;
	margin: 0px;
	background: url('/img/naver.png') no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	padding: 0;
}

.div_square .square {
	position: relative;
	float: left;
	clear: both;
	margin: 0px;
	border: solid 1px;
	text-align: center;
}

.div_square .square_second {
	position: relative;
	margin: 0px;
	float: left;
	border: solid 1px;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(document).ready(
			function() {
				$('.div_square').css('width', $(window).width() - 34);
				$('.div_square').css('height', $(window).height() - 34);
				$(window).resize(
						function() {
							$('.div_square').css('width',
									$(window).width() - 34);
							$('.div_square').css('height',
									$(window).height() - 34);
							$('.square').css('width',
									($(window).width() - 18) / 2 - 10);
							$('.square').css('height',
									($(window).height() - 18) / 2 - 10);
							$('.square_second').css('width',
									($(window).width() - 18) / 2 - 10);
							$('.square_second').css('height',
									($(window).height() - 18) / 2 - 10);
						});
				$('.square').css('width', ($(window).width() - 18) / 2 - 10);
				$('.square').css('height', ($(window).height() - 18) / 2 - 10);
				$('.square_second').css('width',
						($(window).width() - 18) / 2 - 10);
				$('.square_second').css('height',
						($(window).height() - 18) / 2 - 10);

			});
</script>
</head>
<body>
	<div class="div_square">
		<div class="square" onclick="location.href='/board/boardList'">
			<h1>주문하기</h1>
		</div>
		<div class="square_second"
			onclick="location.href='/board/boardWritePage'">
			<h1>판매등록</h1>
		</div>
		<c:if test="${loginStatus == false}">
			<div class="square" onclick="location.href='/signInPage'">
				<h1>로그인</h1>
			</div>
			<div class="square_second" onclick="location.href='/signUpPage'">
				<h1>회원가입</h1>
			</div>
		</c:if>
		<c:if test="${loginStatus == true}">
			<div class="square" onclick="location.href='/logout'">
				<h1>로그아웃</h1>
			</div>
			<div class="square_second" onclick="location.href='/order/orderInfoPage'">
				<h1>주문목록</h1>
			</div>
		</c:if>
	</div>
</body>
</html>