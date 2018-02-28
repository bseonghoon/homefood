<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>HOMEFOOD:리스트</title>
<link rel="stylesheet" type="text/css" href="/css/top.css">
<style type="text/css">
table {
	border-collapse: collapse;
	text-align: center;
	vertical-align: middle;
	border-top: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	margin: auto;
}

table thead th {
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #e7708d;
}

table tbody th {
	width: 150px;
}

table td {
	vertical-align: top;
}

table .even {
	background: #fdf3f5;
}
</style>
<script>
	var page = 1;
	var count = 8;
	var endPage = 1;
	$(document).ready(function() {
		pagingData(page);
	});

	function pagingData(nowPage) {
		page = nowPage;
		$.ajax({
			url : "/board/" + nowPage + "/" + count,
			dataType : "json",
			type : 'get',
			success : function(board) {
				$('#board').children().remove();
				for (i in board) {
					$('#board').append(markUp(board[i],i));
					boardPaging(page);
				}
			}
		});
	}

	function markUp(board, tableSeq) {
		var table = $("<table></table>").prop({
			"id" : "boardTable" + tableSeq,
			"class" : "info_table"
		});
		var tr = $("<tr></tr>");
		var boardSeq = $("<td></td>").html(board.boardSeq);
		var sellerId = $("<td></td>").html(board.sellerId);
		var title = $("<td></td>");
		var href = $("<a></a>").prop("href",
				"/board/detailPage/" + board.boardSeq).html(board.title);
		title.append(href);
		var image = markUpImage(board.imageList[0]);
		
		var pDate = new Date(board.postingDate);
		var postingDate = pDate.getFullYear() + "." + pDate.getMonth() + "." + pDate.getDate() + "." + pDate.getHours() + ":" + pDate.getMinutes();
		var uDatd = new Date(board.updateDate);
		var updateDate = uDatd.getFullYear() + "." + uDatd.getMonth() + "." + uDatd.getDate() + "." + uDatd.getHours() + ":" + uDatd.getMinutes();
		
		var postingDateTd = $("<td></td>").html(postingDate);
		var updateDateTd = $("<td></td>").html(updateDate);
		
		tr.append(boardSeq);
		tr.append(image);
		tr.append(sellerId);
		tr.append(title);
		tr.append(postingDateTd);
		tr.append(updateDateTd);
		
		
		return tr;
	}
	
	function markUpImage(image) {
		var td = $("<td></td>");
		var img = $("<img>").prop({
			"height" : "100",
			"width" : "100"
		});
		
		if(image != null){
			img.prop({
			"src" : "/boardImage/" + image.imagePath
			}).dblclick(function(){
		        location.href = '/boardImage/' + image.imagePath;
		    });;
		}else{
			img.prop({
	            "src" : "/boardImage/basic/basic.jpg"
            });
		}
		td.append(img);
		return td;
	}
	
	function boardPaging(nowPage){
		$.ajax({
			url : "/board/endPage/" + count,
			dataType : "json",
			type : "get",
			success : function(nowEndPage){
				makeBoardPaging(nowEndPage, nowPage);
			}
		});
	}
	
	function makeBoardPaging(nowEndPage, nowPage){
		endPage = nowEndPage;
		var pagingDiv = $("#paging");
		pagingDiv.empty();
		for(var i = nowPage -3; i < nowPage + 3 ; i++){
			if(i < 1 || i > endPage){
				continue;
			}
			var button = $("<button onclick='pagingData(" + i + ")'></button>").html(i);
			pagingDiv.append(button);
		}
	}
</script>
</head>
<body>
	<div class="top">
		<h1>
			<a href="/">HOMEFOOD</a>
		</h1>
	</div>
	<div style='text-align: center; height: 900px;'>
		<table border="1">
			<colgroup>
                <col width="60px">
                <col width="120px">
                <col width="100px">
                <col width="200px">
                <col width="150px">
                <col width="150px">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>대표이미지</th>
					<th>작성자</th>
					<th>제목</th>
					<th>등록일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody id="board">

			</tbody>
		</table>
	</div>
	<div id="paging" style='text-align: center'></div>
</body>
</html>