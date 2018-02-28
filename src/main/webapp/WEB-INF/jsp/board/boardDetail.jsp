<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:상세정보</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=VsMWUhYgi6Zwfodl7ooj&submodules=geocoder"></script>
<script type="text/javascript" src="/js/boardDelete.js"></script>
<script type="text/javascript" src="/js/boardRead.js"></script>
<script type="text/javascript" src="/js/boardUpdate.js"></script>
<link rel="stylesheet" type="text/css" href="/css/top.css">
</head>
<body>
	<div class="top">
		<h1>
			<a href="/">HOMEFOOD</a>
		</h1>
	</div>
	<div >
		<table width="1100px" style="margin: auto;">
			<colgroup>		
			</colgroup>
			<tbody>
				<tr>
					<th>판매자 ID</th>
					<td><input type="text" id="sellerId" disabled></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><textarea id="title" rows="1" cols="150"></textarea></td>
				</tr>
				<tr>
                    <th></th>
                    <td id="image"></td>
				</tr>
				<tr id="foodList">
					<th>음식</th>
				</tr>
				<tr>
					<th>주소</th>
					<td><textarea id="location" rows="1" cols="150"></textarea></td>
				</tr>
				<tr>
					<th></th>
					<td id="map" style="width: 25%; height: 200px;"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="150" rows="30" id="content"></textarea></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="buttonDiv" style="display: none; text-align: center;">
		<a href="#" onclick="updateReady(${boardSeq})">수정</a>
		&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
			onclick="deleteFunction(${boardSeq})">삭제</a>
	</div>
	<div style="text-align: center;">
		<a href="/board/boardList">목록으로</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
			href="#" onclick="location.href='/order/orderPage/' + ${boardSeq}">주문하기</a>
	</div>
	<script>
	$(document).ready(function(){
	    getBoardDetail("${boardSeq}" , "${id}");
	});
	</script>
</body>
</html>