/**
 * 수정
 */

function updateReady(boardSeq) {
	basicUpdateReady(boardSeq);
	foodUpdateReady();
	imageUpdateReady();
}
function basicUpdateReady(boardSeq) {
	$('input').prop("readonly", false);
	$('textarea').prop("readonly",false);
	var buttonDiv = $('#buttonDiv');
	buttonDiv.empty();
	var updateButton = $("<a></a>").html("수정 완료").prop("href", "#").click(
			function() {
				update(boardSeq);
			});
	var blank = $("<a></a)").html("&nbsp&nbsp&nbsp&nbsp");
	var cancleButton = $("<a></a>").html("취소").prop("href", "#").click(
			function() {
				history.back();
				location.reload();
			});
	buttonDiv.append(updateButton).append(blank).append(cancleButton);
}

function foodUpdateReady() {
	// 음식
	var foodTd = $("#foodList").find("td");
	for (var i = 0; i < foodTd.length; i++) {
		var number = foodTd.eq(i).prop("id").substr(4);
		var aTag = $("<a onclick='deleteOneFood(" + number + ")'></a>").prop({
			"href" : "#",
		}).html("삭제");
		foodTd.eq(i).append(aTag);
	}
}

function imageUpdateReady() {
	// 이미지
	var image = $("#image").find("a");
	for (var i = 0; i < image.length; i++) {
		var number = image.eq(i).prop("id").substr(5);
		var aTag = $("<a onclick='deleteOneImage(" + number + ")'></a>").prop({
			"href" : "#"
		}).html("삭제");
		image.eq(i).append(aTag);
	}
}

function update(boardSeq) {
	
	boardUpdate(boardSeq);
	if(deleteImageData.length > 0){
		deleteImageList(boardSeq);
	}

	location.reload();
}

function boardUpdate(boardSeq){
	
	var foodListData = $("#foodList").find("td");
	var foodList = new Array();
	for (var i = 0; i < foodListData.length; i++) {
		var foodName = foodListData.eq(i).find("input").eq(0).val().trim();
		var count = foodListData.eq(i).find("input").eq(1).val().trim();
		var gram = foodListData.eq(i).find("input").eq(2).val().trim();
		var price = foodListData.eq(i).find("input").eq(3).val().trim();
		if(foodName == "" || count == "" || gram == "" || price == ""){
			continue;
		}
		var food = {
			"boardSeq" : boardSeq,
			"number" : foodListData.eq(i).prop("id").substr(4), // id값의 앞 4글자인
			// food 제거
			"foodName" : foodListData.eq(i).find("input").eq(0).val().trim(),
			"count" : foodListData.eq(i).find("input").eq(1).val().trim(),
			"gram" : foodListData.eq(i).find("input").eq(2).val().trim(),
			"price" : foodListData.eq(i).find("input").eq(3).val().trim(),
		}
		foodList.push(food);
	}
	if(foodList.length == 0){
		alert("음식은 최소 1개가 포함되어야 합니다.");
		return;
	}
	var boardData = {
		'boardSeq' : boardSeq,
		'title' : $("#title").val(),
		'content' : $("#content").val(),
		'location' : $("#location").val(),
		'foodList' : foodList
	}
	
	$.ajax({
		url : "/board",
		type : "PUT",
		contentType : "application/json;charset=UTF-8",
		data : JSON.stringify(boardData),
		success : function() {
			if(deleteImageData.length > 0){
				deleteFoodList(boardSeq);
			}
			console.log("basic update success");
		},
		error : function(error){
				console.log(error);
		}
	});
}

var deleteImageData = [];

function deleteOneImage(number) {
	$("#image" + number).remove();
	deleteImageData.push(number);
}

function deleteImageList(boardSeq) {

	for (var i = 0; i < deleteImageData.length; i++) {
		$.ajax({
			url : "/image/one/" + boardSeq + "/" + deleteImageData.pop(),
			type : 'DELETE',
			success : function() {
				console.log("image delete success");
			}

		});
	}
}

var deleteFoodData = [];

function deleteOneFood(number) {

	$("#food" + number).remove();
	deleteFoodData.push(number);
}

function deleteFoodList(boardSeq) {

	for (var i = 0; i < deleteFoodData.length; i++) {
		$.ajax({
			url : "/board/food/" + boardSeq + "/" + deleteFoodData.pop(),
			type : 'DELETE',
			success : function() {
				console.log("food delete success");
			}

		});
	}
}

