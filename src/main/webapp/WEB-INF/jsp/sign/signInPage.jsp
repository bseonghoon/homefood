<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:로그인</title>
 <link rel="stylesheet" type="text/css" href="/css/top.css">

</head>
<body>
    <div class="top">
        <h1>
            <a href="/">HOMEFOOD</a>
        </h1>
    </div>
	<form action="/signInAction" method="post" style='text-align:center'>
		<input type="text" name="id"><br/>
		<input type="password" name = "passwd"><br/>
		<button type="submit">로그인</button> &nbsp;&nbsp; 
		<a href="./signUpPage">회원가입</a>
	</form>
	<div style="text-align: center;">
	판매자: seller/123<br/>
	구매자: consumer/123
	</div>
</body>
</html>