/**
 * 
 */

/**
 * 삭제
 */
function deleteFunction(boardSeq) {
	deleteBasic(boardSeq);
	location.href = "/board/boardList";
}

function deleteBasic(boardSeq) {
	$.ajax({
		url : "/board/" + boardSeq,
		type : 'DELETE',
		success : function() {
			console.log("basic delete success");
		}
	});
}