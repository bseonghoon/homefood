<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:회원 가입</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
.content {
	padding-left: 750px;
	
}
</style>
 <link rel="stylesheet" type="text/css" href="/css/top.css">
</head>
<body>
	<div class="top">
		<h1>
			<a href="/">HOMEFOOD</a>
		</h1>
	</div>
	<div class="content">
		<form action="./signUpAction" method="post" id="signUpForm">
			<table>
				<tr>
					<th>*아이디</th>
					<td><input type="text" name="id" id="id" onchange="changeId()">
						<button type="button" id="dupl"
							onclick="overlapCheck($('#id').val())">중복확인</button></td>
				</tr>
				<tr>
					<th>*비밀번호</th>
					<td><input type="password" name="passwd" id="passwd"
						onchange="passwordCheck()"></td>
				</tr>
				<tr>
					<th>*비밀번호 확인</th>
					<td><input type="password" name="passwdConfirm" id="passwd2"
						onchange="passwordCheck()"></td>
					<td id="passwdCheckTd"></td>
				</tr>
				<tr>
					<th>*회원구분</th>
					<td><input type="radio" name="type" value="s">판매자
						&nbsp; <input type="radio" name="type" value="c">구매자
				</tr>
				<tr>
					<th>email</th>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="location"></td>
				</tr>
			</table>
			<button type="button" onclick="signUp()">회원가입</button>
			<button type="button" onclick="history.back()">취소</button>
		</form>
	</div>
	<script>
		var idCheck = false;
		var passwdCheck = false;
		
		function passwordCheck(){
			var passwd = $('#passwd').val();
			var passwd2 = $('#passwd2').val();
			
			if(passwd == passwd2){
				passwdCheck = true;
				$("#passwdCheckTd").html("비밀번호가 일치합니다.");
			}else{
				passwdCheck = false;
				$("#passwdCheckTd").html("비밀번호가 다릅니다.");
			}
		}
		
		function changeId(){
			idCheck = false;
		}
		
		function overlapCheck(id) {
			$.ajax({
				url : "/signUp/overlapCheck/" + id,
				type : "get",
				dataType : "json",
				success : function(checkResult){
					if(checkResult){
						idCheck = true;
						alert("사용가능한 id입니다.");
					}else{
						alert("중복된 id입니다.");
					}
				}
			});
		}
		
		function signUp(){
			if(idCheck == false){
				alert("id중복체크를 해주세요");
				return;
			}
			if(passwdCheck == false){
				alert("비밀번호가 다릅니다.");
				return;
			}
			if($("input:radio[name=type]:checked").val() == undefined){
				alert("회원 구분을 입력해주세요.")
				return;
			}
			
			var signUpForm = $("#signUpForm");
			alert("회원가입 완료");
			signUpForm.submit();
			event.stopPropagation();
		}
	</script>
</body>
</html>