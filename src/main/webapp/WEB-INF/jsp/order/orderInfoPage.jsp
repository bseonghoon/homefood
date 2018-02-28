<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOMEFOOD:주문내역</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/top.css">

<style type="text/css">
strong>a {
	font-size: 30px;
}
</style>
</head>
<body>
	<div class="top">
		<h1>
			<a href="/">HOMEFOOD</a>
		</h1>
	</div>
	<strong><a href="#" onclick="getOrderInfo('${id}', 1)">주문내역</a></strong>&nbsp;&nbsp;&nbsp;&nbsp;
	<strong><a href="#" onclick="getSellerInfo('${id}', 1)">판매내역</a></strong>
	<div>
		<table id="orderInfo">
			<tr>
				<th>주문번호</th>
				<th>게시판번호</th>
				<th id="type">판매자ID</th>
				<th>주문음식</th>
				<th>주문개수</th>
				<th>단가</th>
				<th>진행상태</th>
			</tr>

		</table>
	</div>
	<div id="paging"></div>
	<div>
		<a href="/">홈으로</a>
	</div>
	<script>
	var orderInfoTh; 
	$(document).ready(function(){
		orderInfoTh = $("#orderInfo").find("tr").eq(0).clone();
		getOrderInfo("${id}", 1);
	})
	var page;
	var count = 10;
	
	function getOrderInfo(consumerId, nowPage){
		$("#type").html("판매자ID");
		page = nowPage;
		$.ajax({
			url : "/order/" + consumerId + "/" + nowPage + "/" + count,
			type: "get",
			dataType : "json",
			success : function(orderInfo){
				$("#orderInfo").empty();
				$("#orderInfo").append(orderInfoTh);
				for(i in orderInfo){
					$("#orderInfo").append(makrUpConsumerInfo(orderInfo[i]));
					boardPaging(consumerId, nowPage);
				}
			},
			erroer : function(a,b,error){
				console.log("error" + error);
			}
		});
	}
	
	function getSellerInfo(sellerId, nowPage){
        page = nowPage;
        $.ajax({
            url : "/order/seller/" + sellerId + "/" + nowPage + "/" + count,
            type: "get",
            dataType : "json",
            success : function(orderInfo){
            	console.log("success");
                $("#orderInfo").empty();
                $("#orderInfo").append(orderInfoTh);
                $("#type").html("구매자ID");
                for(i in orderInfo){
                    $("#orderInfo").append(markUpSellerInfo(orderInfo[i], sellerId));
                    boardSellerPaging(sellerId, nowPage);
                }
            },
            error : function(){
            	alert("권한이 없습니다.")
            }
        });
    }
	
	function markUpOrderInfo(orderInfo){
        var tr = $("<tr></tr>");
        var orderSeqTd = $("<th></th>").html(orderInfo.orderSeq);
        var boardSeqTd = $("<td></td>");
        var aTag = $("<a></a>").prop({
            "href" : "/board/detailPage/" + orderInfo.boardSeq
        }).html(orderInfo.boardSeq);
        boardSeqTd.append(aTag);
        
        var idTd = $("<td></td>").html(orderInfo.id);
        var foodNameTd = $("<td></td>").html(orderInfo.foodName);
        var orderCountTd = $("<td></td>").html(orderInfo.orderCount);
        var priceTd = $("<td></td>").html(orderInfo.price);
        tr.append(orderSeqTd);
        tr.append(boardSeqTd);
        tr.append(idTd);
        tr.append(foodNameTd);
        tr.append(orderCountTd);
        tr.append(priceTd);
        return tr;
    }
	
	function makrUpConsumerInfo(orderInfo){
		
		var tr = markUpOrderInfo(orderInfo);
		
        var orderStatusTd = $("<td></td>");
        if(orderInfo.orderStatus == "n"){
            orderStatusTd.html("미승인");
        }else{
            orderStatusTd.html("승인");
        }
        
        
        tr.append(orderStatusTd);
        return tr;
	}
	
	function markUpSellerInfo(orderInfo, sellerId){
        
		var tr = markUpOrderInfo(orderInfo);

        var orderStatusTd = $("<td></td>");
        if(orderInfo.orderStatus == "n"){
            orderStatusTd.html("<a href='#'>미승인</a>").click(function(){
            	approveOrder(orderInfo.orderSeq, sellerId);
            });
        }else{
            orderStatusTd.html("승인");
        }
        tr.append(orderStatusTd);
        return tr;
    }
	
	function approveOrder(orderSeq, sellerId){
		$.ajax({
			url : "/order/seller/approve/" + orderSeq,
			type : "put",
			success : function(){
				getSellerInfo(sellerId, page);
			}
		})
	}
	
	function boardPaging(consumerId, nowPage){
		$.ajax({
			url : "/order/endPage/" + consumerId + "/"+ count,
			dataType : "json",
			type : "get",
			success : function(nowEndPage){
				makeBoardPaging(consumerId, nowEndPage, nowPage);
			}
		});
	}
	
	function makeBoardPaging(consumerId, nowEndPage, nowPage){
		endPage = nowEndPage;
		var pagingDiv = $("#paging");
		pagingDiv.empty();
		for(var i = nowPage -3; i < nowPage + 3 ; i++){
			if(i < 1 || i > endPage){
				continue;
			}
			var button = $("<button onclick='getOrderInfo(\""+ consumerId + "\"," + i + ")'></button>").html(i);
			pagingDiv.append(button);
		}
	}
	
	function boardSellerPaging(sellerId, nowPage){
        $.ajax({
            url : "/order/seller/endPage/" + sellerId + "/"+ count,
            dataType : "json",
            type : "get",
            success : function(nowEndPage){
                makeBoardSellerPaging(sellerId, nowEndPage, nowPage);
            }
        });
    }
    
    function makeBoardSellerPaging(sellerId, nowEndPage, nowPage){
        endPage = nowEndPage;
        var pagingDiv = $("#paging");
        pagingDiv.empty();
        for(var i = nowPage -3; i < nowPage + 3 ; i++){
            if(i < 1 || i > endPage){
                continue;
            }
            var button = $("<button onclick='getSellerInfo(\""+ sellerId + "\"," + i + ")'></button>").html(i);
            pagingDiv.append(button);
        }
    }
</script>
</body>
</html>