<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:주문 페이지</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/top.css">

</head>
<body>
<div class="top">
        <h1>
            <a href="/">HOMEFOOD</a>
        </h1>
    </div>
	<table>
		<tr id="foodList">
			<th> 음식 목록 </th>
		</tr>
	</table>
	<a href="#" onclick="saveOrderFood(${boardSeq})">주문하기</a>
	<script>
$(document).ready(function(){
	getFoodList("${boardSeq}");
});

function getFoodList(boardSeq){
    $.ajax({
        url: "/board/detail/" + boardSeq,
        type : "GET",
        success : function(board){
            for(i in board.foodList){
                $("#foodList").append(makeOrderList(board.foodList[i]));
            }
        }
    });
}

function makeOrderList(food){
	var td = $("<td></td>").prop("id", "food" + food.number);;
	
	td.append("이름 : " + food.foodName);
	td.append($("<br/>")).append("남은수량 : " + food.count);
	td.append($("<br/>")).append("기준 gram : " + food.gram);
	td.append($("<br/>")).append("단가 : " + food.price);  
	var orderCount = $("<input>").prop({
		"type" : "number",
		"name" : "orderCount",
		"min" : "0",
		"max" : food.count
	});
	td.append($("<br/>")).append("구매 수량:").append(orderCount);
	
	return td;
}


function saveOrderFood(boardSeq){
    var foodList = $("#foodList").find("td");
    for(var i = 0 ; i< foodList.length; i++){
        if(foodList.eq(i).find("[name=orderCount]").val() < 0){
            alert("주문품목의 개수를 확인해주세요");
            return;
        }
    }
    var orderFoodList = new Array();
    
    for(var i = 0 ; i< foodList.length; i++){
    	var orderCount = foodList.eq(i).find("[name=orderCount]").val();
    	if(orderCount == 0 || orderCount == ""){
    		continue;
    	}
        var orderFood = {
            "foodNumber" : foodList.eq(i).prop("id").substr(4),
            "orderCount" : orderCount
        }
        orderFoodList.push(orderFood);
    }
    
    console.log(orderFoodList);
    var orderInfoData = JSON.stringify({
        "boardSeq" : boardSeq,
        "orderFoodList" : orderFoodList
    });
    
    $.ajax({
        url : "/order",
        type : 'post',
        data : orderInfoData,
        contentType:"application/json;charset=UTF-8",
        success : function(){
            console.log("success order");
            location.href = "/order/orderInfoPage";
        },
        error : function(error){
        	alert(error.responseText);
        }
    });
}


</script>
</body>
</html>