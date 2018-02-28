<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:등록</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/top.css">
<style type="text/css">
tr {
	display: block;
}
</style>
</head>
<body>
	<div class="top">
		<h1>
			<a href="/">HOMEFOOD</a>
		</h1>
	</div>
	<div style='text-align:center; margin: auto;'>
		<table style='text-align:center; margin: auto; table-layout: fixed; width: 1015px;'>
			<tr>
				<th>*제목</th>
				<td colspan="4"><textarea id="title" rows="1" cols="150"></textarea></td>
			</tr>
			<tr>
				<th></th>
				<td colspan="4">
					<button onclick="addImageTag()">이미지 추가</button>
				</td>
				<td>
					<button onclick="addFoodTag()">음식 추가</button>
				</td>
			</tr>
			<tr id="image" style="display: none">
				<th>이미지</th>
				<td colspan="1"><input type="file" name="imageFile"></td>
				<td colspan="1">대표이미지<input type="radio" name="profile"></td>
			</tr>
			<tr id="food" style="display: none">
				<th>*판매 음식</th>
				<td>음식이름: <input type="text" name="foodName"></td>
				<td>기준 gram: <input type="number" min="0" name="foodGram"></td>
				<td>판매 수량: <input type="number" min="0" name="foodCount"></td>
				<td>단가: <input type="number" min="0" name="foodPrice"></td>
			</tr>
			<!--  
				<tr>
					<th></th>
					<td><input type="date" name="date"></td>
				</tr>
				-->
			<tr>
				<th>*주소</th>
				<td><textarea id="location" cols="150" rows="1"></textarea></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="150" id="content"></textarea>
			</tr>
		</table>
		<button id="post" onclick="post()">등록</button>
	</div>
	<script>
		var imageValue = 0;
		var foodValue = 0;
		$(document).ready(function() {
			addImageTag();
			addFoodTag();
			writeReady();
		});

		function addImageTag() {
			var image = $("#image").clone();
			image.prop("id", "");
			image.css("display", "block");
			if (imageValue == 0) {
				image.find("input[name=profile]").val(imageValue).prop(
						"checked", true);
			} else {
				image.find("input[name=profile]").val(imageValue).prop(
						"checked", false);
			}
			imageValue++;
			
			
			$("#image").before(image);
		}

		function addFoodTag() {
			var food = $("#food").clone();
			food.prop("id", "");
			food.css("display", "block");
			food.find("input").val('');
			foodValue++;
			$("#food").before(food);
		}
		
		function writeReady(){
			$.ajax({
				url : "/board/writeReady",
				type : "get",
				dataType: "json",
				success : function(user){
					$("#location").val(user.location);
				}
			});
		}
		
		function getExtensionOfFilename(filename) 
		{
			var _fileLen = filename.length; 
			var _lastDot = filename.lastIndexOf('.'); 
			var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();
			return _fileExt; 
		}


		function makeFormData(){
		    var formData = new FormData();
            
		    for(var i = 0;i < imageValue; i++){
		    	
		    }
		    
            for (var i = 0; i < imageValue; i++) {
                formData.append("imageFile",
                        $("input[name=imageFile]")[i].files[0]);
            }
            for (var i = 0; i < foodValue; i++) {
                var foodName = $("input[name = foodName]:eq(" + i + ")").val().trim();
                var count =$("input[name=foodCount]:eq(" + i + ")").val().trim();
                var gram = $("input[name=foodGram]:eq(" + i + ")").val().trim();
                var price = $("input[name=foodPrice]:eq(" + i + ")").val().trim();
                
                if(foodName == "" || count == "" || gram == "" || price == ""){
                    continue;
                }
                
                formData.append("foodList["+ i +"].foodName", foodName);
                formData.append("foodList["+ i +"].count", count);
                formData.append("foodList["+ i +"].gram", gram);
                formData.append("foodList["+ i +"].price", price);
            }
            formData.append("profile", $("input:radio[name=profile]:checked")
                    .val());
            formData.append("title", $("#title").val());
            formData.append("content", $("#content").val());
            formData.append("location", $("#location").val());
            
            return formData;
		}
		
		function post() {
			var formData = makeFormData();
			$.ajax({
				url : '/board',
				type : 'post',
				cache : false,
				contentType : false,
				processData : false,
				data : formData,
				success : function() {
					console.log("board Upload success");
					location.href= "/board/boardList";
				},
				error : function(error){
					alert(error.responseText);
				}
			});
		}
	</script>
</body>
</html>