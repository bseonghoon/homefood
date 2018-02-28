/**
 * 정보 스캔
 */

function getBoardDetail(boardSeq, id){
	getBoard(boardSeq, id);
    $("input").prop("readonly", true);
    $("textarea").prop("readonly", true);
}

function getBoard(boardSeq, id){
	$.ajax({
        url : "/board/detail/" + boardSeq ,
        type : 'get',
        dataType : 'json',
        success : function(board){
        	boardAccess(board,id);
        	markUpBoardDetail(board);
        	for(i in board.imageList){
				$("#image").append(makeImage(board.imageList[i]));
			}
        	$("#foodList").find("td").eq(0).prop({
        		"rowspan" : board.foodList.length
        	});
        	for(i in board.foodList){
				$("#foodList").append(makeFood(board.foodList[i]));
			}
        	getMap(board.location);
	    }
	});
}

function markUpBoardDetail(board){
	$('#sellerId').val(board.sellerId);
	$('#title').val(board.title);
	$('#content').val(board.content);
	$('#location').val(board.location);
}

function boardAccess(board, id){
	if(board.sellerId == id){
		$("#buttonDiv").css({
			"display" : "block"
		});
	}
}

/**
 * 지도
 */
function getMap(location){
	  var map = new naver.maps.Map('map');
    var myaddress = location;// 도로명 주소나 지번 주소만 가능 (건물명 불가!!!!)
    naver.maps.Service.geocode({address: myaddress}, function(status, response) {
        if (status !== naver.maps.Service.Status.OK) {
            console.log("지도를 불러올 수 없습니다.");
        }
        var result = response.result;
        // 검색 결과 갯수: result.total
        // 첫번째 결과 결과 주소: result.items[0].address
        // 첫번째 검색 결과 좌표: result.items[0].point.y, result.items[0].point.x
        var myaddr = new naver.maps.Point(result.items[0].point.x, result.items[0].point.y);
        map.setCenter(myaddr); // 검색된 좌표로 지도 이동
        // 마커 표시
        var marker = new naver.maps.Marker({
          position: myaddr,
          map: map
        });
    });
}

function makeImage(image){
	var td = $("<a></a>").prop("id","image" + image.number);
	var img = $("<img>").prop({
		"src" : "/boardImage/" + image.imagePath,
		"height" : "100",
		"width" : "100"
	}).dblclick(function(){
		location.href = '/boardImage/' + image.imagePath;
	});
	
	td.append(img);
	return td;
}

function makeFood(food){
	var tr = $("<tr></tr>")
	var td = $("<td></td>");
	var foodName = $("<input>").prop({
		"type" : "text",
		"name" : "foodName",
		"value" : food.foodName,
		"readonly" :true
	});
	var foodCount = $("<input>").prop({
        "type" : "text",
        "name" : "foodCount",
        "value" : food.count,
        "readonly" :true
    });
	var foodGram = $("<input>").prop({
        "type" : "text",
        "name" : "foodGram",
        "value" : food.gram,
        "readonly" :true
    });
    var foodPrice = $("<input>").prop({
        "type" : "text",
        "name" : "foodPrice",
        "value" : food.price,
        "readonly" :true
    });
    
    td.append("이름 : ").append(foodName);
    td.append("남은수량 : ").append(foodCount);
    td.append("기준 gram : ").append(foodGram);
    td.append("단가 : ").append(foodPrice);
    
	td.prop("id", "food" + food.number);
	
	tr.append(td);
	return tr;
}